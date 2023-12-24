package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.CartEntry;


public interface CartEntryService {

    CartEntry saveOrUpdate(CartEntry orderEntry);

    void remove(CartEntry cartEntry);

}
