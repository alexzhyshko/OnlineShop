package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.DeliveryMode;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryModeDao extends JpaRepository<DeliveryMode, Long> {

    Optional<DeliveryMode> findByName(DeliveryModeEnum deliveryModeEnum);

}
