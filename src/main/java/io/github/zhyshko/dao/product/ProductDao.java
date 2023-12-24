package io.github.zhyshko.dao.product;

import io.github.zhyshko.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findByExternalId(UUID externalId);

    List<Product> findAllByCategoriesExternalIdIn(List<UUID> categoryExternalIds);

    List<Product> findAllByNameContains(String term);
}
