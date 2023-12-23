package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dao.order.CartDao;
import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.model.order.Cart;
import io.github.zhyshko.service.order.DeliveryModeService;
import io.github.zhyshko.service.order.OrderStatusService;
import io.github.zhyshko.service.order.PaymentModeService;
import io.github.zhyshko.service.order.PaymentStatusService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {CartEntryMapper.class})
public abstract class CartMapper {

    @Autowired
    protected OrderStatusService orderStatusService;
    @Autowired
    protected PaymentStatusService paymentStatusService;
    @Autowired
    protected DeliveryModeService deliveryModeService;
    @Autowired
    protected PaymentModeService paymentModeService;
    @Autowired
    protected CartEntryMapper cartEntryMapper;
    @Autowired
    protected CartDao cartDao;

    public Cart toModel(CartData cartData) {
        if (cartData == null) {
            return null;
        }

        return cartDao.findByExternalId(cartData.getExternalId())
                .map(c -> {
                    if (cartData.getExternalId() != null) {
                        c.setExternalId(cartData.getExternalId());
                    }
                    if (cartData.getDeliveryMode() != null) {
                        c.setDeliveryMode(deliveryModeService.getOrCreate(cartData.getDeliveryMode()));
                    }
                    if(cartData.getPaymentMode() != null) {
                        c.setPaymentMode(paymentModeService.getOrCreate(cartData.getPaymentMode()));
                    }
                    if(cartData.getCartEntryList() != null) {
                        c.setCartEntryList(cartEntryMapper.toModelList(cartData.getCartEntryList()));
                    }
                    return cartDao.save(c);
                }).orElseGet(
                        () -> cartDao.save(createCart(cartData)));
    }

    @Mapping(target = "paymentMode", expression = "java(cart.getPaymentMode().getName())")
    @Mapping(target = "deliveryMode", expression = "java(cart.getDeliveryMode().getName())")
    public abstract CartData toDto(Cart cart);

    private Cart createCart(CartData cartData) {
        Cart.CartBuilder<?, ?> cart = Cart.builder();

        cart.externalId(cartData.getExternalId());
        cart.paymentMode(paymentModeService.getOrCreate(cartData.getPaymentMode()));
        cart.deliveryMode(deliveryModeService.getOrCreate(cartData.getDeliveryMode()));
        cart.cartEntryList(cartEntryMapper.toModelList(cartData.getCartEntryList()));

        return cart.build();
    }

}
