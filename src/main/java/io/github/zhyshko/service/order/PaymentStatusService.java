package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.PaymentStatus;
import io.github.zhyshko.model.order.PaymentStatusEnum;

public interface PaymentStatusService {

    PaymentStatus getOrCreate(PaymentStatusEnum paymentStatusEnum);

}
