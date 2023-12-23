package io.github.zhyshko.mapper.wsdto.product;

import io.github.zhyshko.dto.product.ProductAttributeData;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.ProductAttributeWsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class ProductAttributeWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract ProductAttributeWsDto toWsDto(ProductAttributeData productAttributeData);

    public abstract ProductAttributeData toDto(ProductAttributeWsDto productAttributeWsDto);

    public abstract List<ProductAttributeWsDto> toWsDtoList(List<ProductAttributeData> productAttributeData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }
}
