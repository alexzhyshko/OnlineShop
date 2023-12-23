package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.dto.order.CartEntryData;
import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.CartFacade;
import io.github.zhyshko.mapper.dto.order.CartEntryMapper;
import io.github.zhyshko.mapper.dto.order.CartMapper;
import io.github.zhyshko.model.order.Cart;
import io.github.zhyshko.service.order.CartEntryService;
import io.github.zhyshko.service.order.CartService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DefaultCartFacade implements CartFacade {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartEntryService cartEntryService;
    @Autowired
    private CartEntryMapper cartEntryMapper;

    @Override
    public void addToCart(UUID productExternalId, Integer quantity) {
        Cart currentCart = cartService.getCurrentCart();

        CartData cartData = cartMapper.toDto(currentCart);

        Optional<CartEntryData> existingEntry = cartData.getCartEntryList().stream()
                .filter(e -> e.getProduct().getExternalId().equals(productExternalId))
                .findFirst();

        existingEntry.ifPresentOrElse(e -> {
            e.setQuantity(e.getQuantity() + quantity);
            e.setAmount(e.getProduct().getPrice() * e.getQuantity());
        }, () -> {
            CartEntryData newEntry = CartEntryData.builder()
                    .quantity(quantity)
                    .product(ProductData.builder().externalId(productExternalId).build())
                    .build();
            cartData.getCartEntryList().add(newEntry);
        } );

        cartService.save(cartMapper.toModel(cartData));
    }
}
