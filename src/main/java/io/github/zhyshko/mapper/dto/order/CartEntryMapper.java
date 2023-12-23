package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dto.order.CartEntryData;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.model.order.CartEntry;
import io.github.zhyshko.service.order.CartEntryService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ProductMapper.class})
public abstract class CartEntryMapper {

    @Autowired
    protected CartEntryService cartEntryService;
    @Autowired
    protected ProductMapper productMapper;

    public CartEntry toModel(CartEntryData cartEntryData) {
        if (cartEntryData == null) {
            return null;
        }

        return cartEntryService.saveOrUpdate(createCartEntry(cartEntryData));
    }
    public abstract CartEntryData toDto(CartEntry cartEntry);

    public abstract List<CartEntry> toModelList(List<CartEntryData> cartEntryDataList);

    private CartEntry createCartEntry(CartEntryData cartEntryData) {
        CartEntry.CartEntryBuilder<?, ?> cartEntry = CartEntry.builder();

        cartEntry.externalId(cartEntryData.getExternalId()==null?UUID.randomUUID():cartEntryData.getExternalId());
        cartEntry.product(productMapper.toModel(cartEntryData.getProduct()));
        cartEntry.timeCreated(LocalDateTime.now());
        cartEntry.amount(cartEntryData.getAmount());
        cartEntry.quantity(cartEntryData.getQuantity());

        CartEntry result = cartEntry.build();

        result.setAmount(result.getProduct().getPrice() * result.getQuantity());

        return result;
    }

}
