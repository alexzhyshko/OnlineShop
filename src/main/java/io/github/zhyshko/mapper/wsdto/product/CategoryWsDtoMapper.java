package io.github.zhyshko.mapper.wsdto.product;

import io.github.zhyshko.dto.product.CategoryData;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.CategoryWsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class CategoryWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract CategoryWsDto toWsDto(CategoryData categoryData);

    public abstract CategoryData toDto(CategoryWsDto categoryWsDto);

    public abstract List<CategoryWsDto> toWsDtoList(List<CategoryData> categoryData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }
}
