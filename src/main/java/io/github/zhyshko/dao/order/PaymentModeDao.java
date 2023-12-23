package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.PaymentMode;
import io.github.zhyshko.model.order.PaymentModeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentModeDao extends JpaRepository<PaymentMode, Long> {

    Optional<PaymentMode> findByName(PaymentModeEnum deliveryModeEnum);

}
