package io.github.zhyshko.mapper.wsdto.review;

import io.github.zhyshko.dto.review.ReviewEntryData;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.review.ReviewEntryWsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ReviewEntryWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract ReviewEntryWsDto toWsDto(ReviewEntryData reviewEntryData);

    public abstract ReviewEntryData toDto(ReviewEntryWsDto reviewEntryWsDto);

    public abstract List<ReviewEntryWsDto> toWsDtoList(List<ReviewEntryData> reviewEntryData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
