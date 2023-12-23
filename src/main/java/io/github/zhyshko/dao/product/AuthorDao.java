package io.github.zhyshko.dao.product;

import io.github.zhyshko.model.product.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorDao extends JpaRepository<Author, Long> {

    Optional<Author> findByExternalId(UUID externalId);

}
