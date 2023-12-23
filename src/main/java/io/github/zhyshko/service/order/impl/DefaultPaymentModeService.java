package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.DeliveryModeDao;
import io.github.zhyshko.dao.order.PaymentModeDao;
import io.github.zhyshko.model.order.DeliveryMode;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.order.PaymentMode;
import io.github.zhyshko.model.order.PaymentModeEnum;
import io.github.zhyshko.service.order.DeliveryModeService;
import io.github.zhyshko.service.order.PaymentModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultPaymentModeService implements PaymentModeService {

    @Autowired
    private PaymentModeDao paymentModeDao;

    @Override
    public PaymentMode getOrCreate(PaymentModeEnum paymentModeEnum) {
        return paymentModeDao.findByName(paymentModeEnum).orElseGet(() -> createDeliveryMode(paymentModeEnum));
    }

    private PaymentMode createDeliveryMode(PaymentModeEnum paymentModeEnum) {
        return paymentModeDao.save(PaymentMode.builder()
                .externalId(UUID.randomUUID())
                .name(paymentModeEnum)
                .build());

    }
}
