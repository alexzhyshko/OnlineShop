package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.OrderDao;
import io.github.zhyshko.model.order.*;
import io.github.zhyshko.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void save(Order order) {
        this.orderDao.save(order);
    }

    public Order saveOrUpdate(Order order) {
        return orderDao.findByExternalId(order.getExternalId())
                .map(oe -> {
                    order.setId(oe.getId());
                    return orderDao.save(order);
                })
                .orElseGet(() -> orderDao.save(order));
    }

    @Override
    public boolean userPaidOrdersExist(UUID userExternalId) {
        return orderDao.existsByOwnerExternalIdAndPaymentStatusNameEquals(userExternalId, PaymentStatusEnum.PAID);
    }

    @Override
    public List<Order> getUserOrders(UUID currentUserExternalId) {
        return orderDao.findAllByOwnerExternalId(currentUserExternalId);
    }

    @Override
    public Order getByExternalId(UUID externalId) {
        return orderDao.findByExternalId(externalId).orElseThrow(() -> new RuntimeException("No order found"));
    }
}
