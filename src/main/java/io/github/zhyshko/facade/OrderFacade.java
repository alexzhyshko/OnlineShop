package io.github.zhyshko.facade;

import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.dto.order.OrderData;

import java.util.UUID;

public interface OrderFacade {
    OrderData createOrder(CartData cartData);

    void payOrder(UUID order);

    OrderData getOrder(UUID orderExternalId);

    void addReview(OrderData orderData);
}
