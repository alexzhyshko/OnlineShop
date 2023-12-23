package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.OrderStatusDao;
import io.github.zhyshko.model.order.OrderStatus;
import io.github.zhyshko.model.order.OrderStatusEnum;
import io.github.zhyshko.service.order.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultOrderStatusService implements OrderStatusService {

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Override
    public OrderStatus getOrCreate(OrderStatusEnum paymentModeEnum) {
        return orderStatusDao.findByName(paymentModeEnum).orElseGet(() -> createOrderStatus(paymentModeEnum));
    }

    private OrderStatus createOrderStatus(OrderStatusEnum paymentModeEnum) {
        return orderStatusDao.save(OrderStatus.builder()
                .externalId(UUID.randomUUID())
                .name(paymentModeEnum)
                .build());

    }
}
