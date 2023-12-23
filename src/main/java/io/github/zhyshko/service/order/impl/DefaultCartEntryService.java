package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.CartEntryDao;
import io.github.zhyshko.dao.order.OrderEntryDao;
import io.github.zhyshko.model.order.CartEntry;
import io.github.zhyshko.model.order.OrderEntry;
import io.github.zhyshko.service.order.CartEntryService;
import io.github.zhyshko.service.order.OrderEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCartEntryService implements CartEntryService {

    private CartEntryDao cartEntryDao;

    @Autowired
    public DefaultCartEntryService(CartEntryDao cartEntryDao) {
        this.cartEntryDao = cartEntryDao;
    }

    @Override
    public CartEntry saveOrUpdate(CartEntry cartEntry) {
        return cartEntryDao.findByExternalId(cartEntry.getExternalId())
                .map(ce -> {
                    cartEntry.setId(ce.getId());
                    return cartEntryDao.save(cartEntry);
                })
                .orElseGet(() -> cartEntryDao.save(cartEntry));
    }

}
