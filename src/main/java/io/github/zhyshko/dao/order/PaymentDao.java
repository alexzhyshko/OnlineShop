package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

    Optional<Payment> findByExternalId(UUID externalId);

}
