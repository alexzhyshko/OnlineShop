package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderEntryDao extends JpaRepository<OrderEntry, Long> {

    Optional<OrderEntry> findByExternalId(UUID externalId);

}
