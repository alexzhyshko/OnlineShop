package io.github.zhyshko.dao.review;

import io.github.zhyshko.model.review.ReviewEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewEntryDao extends JpaRepository<ReviewEntry, Long> {

    Optional<ReviewEntry> findByExternalId(UUID externalId);

}
