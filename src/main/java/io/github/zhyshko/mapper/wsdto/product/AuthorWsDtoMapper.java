package io.github.zhyshko.mapper.wsdto.product;

import io.github.zhyshko.dto.product.AuthorData;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.AuthorWsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class AuthorWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract AuthorWsDto toWsDto(AuthorData authorData);

    public abstract AuthorData toDto(AuthorWsDto authorWsDto);

    public abstract List<AuthorWsDto> toWsDtoList(List<AuthorData> authorData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
