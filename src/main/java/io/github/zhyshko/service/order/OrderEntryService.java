package io.github.zhyshko.service.order;

import io.github.zhyshko.model.order.OrderEntry;

import java.util.List;
import java.util.UUID;

public interface OrderEntryService {

    OrderEntry saveOrUpdate(OrderEntry orderEntry);
}
