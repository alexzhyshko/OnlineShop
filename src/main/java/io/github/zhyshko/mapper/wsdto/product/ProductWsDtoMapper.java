package io.github.zhyshko.mapper.wsdto.product;

import io.github.zhyshko.dto.product.*;
import io.github.zhyshko.model.product.*;
import io.github.zhyshko.service.product.ProductService;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.ProductWsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {AuthorWsDtoMapper.class, PublisherWsDtoMapper.class, ProductAttributeWsDtoMapper.class, CategoryWsDtoMapper.class})
public abstract class ProductWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract ProductWsDto toWsDto(ProductData productData);

    public abstract ProductData toDto(ProductWsDto productWsDto);

    public abstract List<ProductWsDto> toWsDtoList(List<ProductData> productData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
