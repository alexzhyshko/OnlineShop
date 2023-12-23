package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.DeliveryMode;
import io.github.zhyshko.model.order.DeliveryModeEnum;

public interface DeliveryModeService {

    DeliveryMode getOrCreate(DeliveryModeEnum deliveryModeEnum);

}
