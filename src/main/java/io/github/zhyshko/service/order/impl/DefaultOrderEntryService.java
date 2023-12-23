package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.OrderEntryDao;
import io.github.zhyshko.model.order.OrderEntry;
import io.github.zhyshko.service.order.OrderEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultOrderEntryService implements OrderEntryService {

    private OrderEntryDao orderEntryDao;

    @Autowired
    public DefaultOrderEntryService(OrderEntryDao orderEntryDao) {
        this.orderEntryDao = orderEntryDao;
    }

    @Override
    public OrderEntry saveOrUpdate(OrderEntry orderEntry) {
        return orderEntryDao.findByExternalId(orderEntry.getExternalId())
                .map(oe -> {
                    orderEntry.setId(oe.getId());
                    return orderEntryDao.save(orderEntry);
                })
                .orElseGet(() -> orderEntryDao.save(orderEntry));
    }
}
