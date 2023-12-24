package io.github.zhyshko.dao.product;

import io.github.zhyshko.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    Optional<Category> findByExternalId(UUID externalId);

    @Query(value = """
SELECT c.* FROM categories as c WHERE c.supercategory_id IS NULL
""", nativeQuery = true)
    List<Category> findAllBySupercategoryIdIsNull();
}
