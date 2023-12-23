package io.github.zhyshko.mapper.wsdto.product;

import io.github.zhyshko.dao.product.PublisherDao;
import io.github.zhyshko.dto.product.PublisherData;
import io.github.zhyshko.dto.product.PublisherData;
import io.github.zhyshko.model.product.Publisher;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.PublisherWsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class PublisherWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract PublisherWsDto toWsDto(PublisherData publisherData);

    public abstract PublisherData toDto(PublisherWsDto publisherWsDto);

    public abstract List<PublisherWsDto> toWsDtoList(List<PublisherData> publisherData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
