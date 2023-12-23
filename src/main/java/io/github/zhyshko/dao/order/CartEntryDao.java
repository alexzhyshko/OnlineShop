package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartEntryDao extends JpaRepository<CartEntry, Long> {

    Optional<CartEntry> findByExternalId(UUID externalId);

}
