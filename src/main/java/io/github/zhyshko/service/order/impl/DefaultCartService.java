package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.CartDao;
import io.github.zhyshko.model.order.Cart;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.order.PaymentMode;
import io.github.zhyshko.model.order.PaymentModeEnum;
import io.github.zhyshko.service.order.CartService;
import io.github.zhyshko.service.order.DeliveryModeService;
import io.github.zhyshko.service.order.PaymentModeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultCartService implements CartService {

    @Autowired
    private DeliveryModeService deliveryModeService;
    @Autowired
    private PaymentModeService paymentModeService;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private HttpSession httpSession;

    @Override
    public Cart createCart() {
        return cartDao.save(Cart.builder()
                .externalId(UUID.randomUUID())
                .deliveryMode(deliveryModeService.getOrCreate(DeliveryModeEnum.HOME))
                .paymentMode(paymentModeService.getOrCreate(PaymentModeEnum.CASH))
                .build());
    }

    @Override
    public Cart getCurrentCart() {
        System.out.println((UUID)httpSession.getAttribute("userId"));
        return cartDao.findCartByUserExternalId((UUID)httpSession.getAttribute("userId"))
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public void save(Cart model) {
        cartDao.save(model);
    }
}
