package io.github.zhyshko.dao.order;

import io.github.zhyshko.model.order.Cart;
import io.github.zhyshko.model.order.DeliveryMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartDao  extends JpaRepository<Cart, Long> {

    @Query(value = """
SELECT c.* FROM carts as c JOIN users as u ON u.cart_id=c.id
WHERE u.external_id=:userExternalId
""", nativeQuery = true)
    Optional<Cart> findCartByUserExternalId(UUID userExternalId);

    Optional<Cart> findByExternalId(UUID externalId);
}
