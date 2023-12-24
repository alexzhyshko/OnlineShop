package io.github.zhyshko.facade;

import io.github.zhyshko.dto.order.CartEntryData;

import java.util.UUID;

public interface CartFacade {

    void addToCart(UUID productExternalId, Integer quantity);

    void changeCount(UUID productExternalId, Integer quantity);

    void removeEntry(UUID productExternalId);

}
