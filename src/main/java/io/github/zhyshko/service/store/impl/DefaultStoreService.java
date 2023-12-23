package io.github.zhyshko.service.store.impl;

import io.github.zhyshko.service.store.StoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultStoreService implements StoreService {

    @Value("${store.externalId}")
    private UUID storeExternalId;

    @Override
    public UUID getStoreExternalId() {
        return storeExternalId;
    }

}
