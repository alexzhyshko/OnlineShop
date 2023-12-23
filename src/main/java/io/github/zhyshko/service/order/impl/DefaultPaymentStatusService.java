package io.github.zhyshko.service.order.impl;

import io.github.zhyshko.dao.order.PaymentStatusDao;
import io.github.zhyshko.model.order.PaymentStatus;
import io.github.zhyshko.model.order.PaymentStatusEnum;
import io.github.zhyshko.service.order.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultPaymentStatusService implements PaymentStatusService {

    @Autowired
    private PaymentStatusDao paymentStatusDao;

    @Override
    public PaymentStatus getOrCreate(PaymentStatusEnum paymentStatusEnum) {
        return paymentStatusDao.findByName(paymentStatusEnum).orElseGet(() -> createDeliveryMode(paymentStatusEnum));
    }

    private PaymentStatus createDeliveryMode(PaymentStatusEnum paymentStatusEnum) {
        return paymentStatusDao.save(PaymentStatus.builder()
                .externalId(UUID.randomUUID())
                .name(paymentStatusEnum)
                .build());

    }
}
