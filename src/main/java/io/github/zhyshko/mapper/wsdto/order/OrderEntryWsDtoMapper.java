package io.github.zhyshko.mapper.wsdto.order;

import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.mapper.wsdto.product.ProductWsDtoMapper;
import io.github.zhyshko.mapper.wsdto.review.ReviewEntryWsDtoMapper;
import io.github.zhyshko.model.order.OrderEntry;
import io.github.zhyshko.service.order.OrderEntryService;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.order.OrderEntryWsDto;
import io.github.zhyshko.wsdto.product.ProductWsDto;
import io.github.zhyshko.wsdto.review.ReviewEntryWsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ReviewEntryWsDtoMapper.class, ProductWsDtoMapper.class})
public abstract class OrderEntryWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract OrderEntryWsDto toWsDto(OrderEntryData orderEntryData);

    public abstract OrderEntryData toDto(OrderEntryWsDto orderEntryWsDto);

    public abstract List<OrderEntryWsDto> toWsDtoList(List<OrderEntryData> orderEntryData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
