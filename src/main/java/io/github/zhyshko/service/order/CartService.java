package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.Cart;

public interface CartService {

    Cart createCart();

    Cart getCurrentCart();

    void save(Cart model);
}
