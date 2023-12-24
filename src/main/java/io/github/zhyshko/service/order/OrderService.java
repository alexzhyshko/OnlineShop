package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.Order;

import java.util.UUID;

public interface OrderService {

    void save(Order order);

    boolean userOrdersExist(UUID userExternalId);
}
