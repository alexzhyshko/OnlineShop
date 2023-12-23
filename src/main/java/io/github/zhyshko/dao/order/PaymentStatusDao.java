package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.PaymentStatus;
import io.github.zhyshko.model.order.PaymentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusDao extends JpaRepository<PaymentStatus, Long> {

    Optional<PaymentStatus> findByName(PaymentStatusEnum deliveryModeEnum);

}
