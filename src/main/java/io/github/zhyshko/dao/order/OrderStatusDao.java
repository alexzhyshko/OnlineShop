package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.OrderStatus;
import io.github.zhyshko.model.order.OrderStatusEnum;
import io.github.zhyshko.model.order.PaymentMode;
import io.github.zhyshko.model.order.PaymentModeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusDao extends JpaRepository<OrderStatus, Long> {

    Optional<OrderStatus> findByName(OrderStatusEnum orderStatusEnum);

}
