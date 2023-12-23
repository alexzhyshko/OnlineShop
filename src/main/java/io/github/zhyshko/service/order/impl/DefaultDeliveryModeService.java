package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.DeliveryModeDao;
import io.github.zhyshko.model.order.DeliveryMode;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.service.order.DeliveryModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultDeliveryModeService implements DeliveryModeService {

    @Autowired
    private DeliveryModeDao deliveryModeDao;

    @Override
    public DeliveryMode getOrCreate(DeliveryModeEnum deliveryModeEnum) {
        return deliveryModeDao.findByName(deliveryModeEnum).orElseGet(() -> createDeliveryMode(deliveryModeEnum));
    }

    private DeliveryMode createDeliveryMode(DeliveryModeEnum deliveryModeEnum) {
        return deliveryModeDao.save(DeliveryMode.builder()
                .externalId(UUID.randomUUID())
                .name(deliveryModeEnum)
                .build());

    }
}
