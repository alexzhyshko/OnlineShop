package io.github.zhyshko.mapper.wsdto.user;

import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.user.UserWsDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class UserWsDtoMapper {

    @Autowired
    private StoreService storeService;

    @Mappings({
            @Mapping(target = "store", expression = "java( createStoreWsDto() )"),
    })
    public abstract UserWsDto toWsDto(UserData userData);

    public abstract UserData toDto(UserWsDto userWsDto);

    public abstract List<UserWsDto> toWsDtoList(List<UserData> userData);

    protected StoreWsDto createStoreWsDto() {
        return StoreWsDto.builder()
                .externalId(storeService.getStoreExternalId())
                .build();
    }

}
