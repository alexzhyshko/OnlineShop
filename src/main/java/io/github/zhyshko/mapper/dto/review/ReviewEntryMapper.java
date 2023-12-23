package io.github.zhyshko.mapper.dto.review;

import io.github.zhyshko.dao.review.ReviewEntryDao;
import io.github.zhyshko.dto.review.ReviewEntryData;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.model.review.ReviewEntry;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ProductMapper.class})
public abstract class ReviewEntryMapper {

    @Autowired
    protected ReviewEntryDao reviewEntryDao;

    public ReviewEntry toModel(ReviewEntryData orderData) {
        if (orderData == null) {
            return null;
        }

        return findAndPopulateIfExists(orderData)
                .orElseGet(() -> reviewEntryDao.save(createReviewEntry(orderData)));
    }

    public abstract ReviewEntryData toDto(ReviewEntry order);

    public List<ReviewEntryData> toDtoList(List<ReviewEntry> entries) {
        return entries.stream().map(this::toDto).toList();
    }
    private Optional<ReviewEntry> findAndPopulateIfExists(ReviewEntryData reviewEntryData) {
        return reviewEntryDao.findByExternalId(reviewEntryData.getExternalId())
                .map(re -> {
                    re.setMark(reviewEntryData.getMark());
                    re.setTimeCreated(reviewEntryData.getTimeCreated());
                    return reviewEntryDao.save(re);
                });
    }

    private ReviewEntry createReviewEntry(ReviewEntryData reviewEntryData) {
        ReviewEntry.ReviewEntryBuilder<?, ?> reviewEntry = ReviewEntry.builder();

        reviewEntry.externalId(reviewEntryData.getExternalId());
        reviewEntry.mark(reviewEntryData.getMark());
        reviewEntry.timeCreated(reviewEntryData.getTimeCreated());

        return reviewEntry.build();
    }

}
