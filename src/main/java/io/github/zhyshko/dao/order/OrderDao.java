package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

    Optional<Order> findByExternalId(UUID externalId);

    boolean existsByOwnerExternalId(UUID userExternalId);

    List<Order> findAllByOwnerExternalId(UUID currentUserExternalId);
}
