package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.OrderStatus;
import io.github.zhyshko.model.order.OrderStatusEnum;

public interface OrderStatusService {

    OrderStatus getOrCreate(OrderStatusEnum orderStatusEnum);

}
