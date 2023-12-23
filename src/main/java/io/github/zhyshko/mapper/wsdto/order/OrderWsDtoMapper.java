package io.github.zhyshko.mapper.wsdto.order;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.mapper.wsdto.user.UserWsDtoMapper;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.order.OrderWsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {OrderEntryWsDtoMapper.class, UserWsDtoMapper.class, })
public abstract class OrderWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract OrderWsDto toWsDto(OrderData orderData);

    public abstract OrderData toDto(OrderWsDto orderWsDto);

    public abstract List<OrderWsDto> toWsDtoList(List<OrderData> orderDataList);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
