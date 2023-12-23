package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.PaymentMode;
import io.github.zhyshko.model.order.PaymentModeEnum;

public interface PaymentModeService {

    PaymentMode getOrCreate(PaymentModeEnum deliveryModeEnum);

}
