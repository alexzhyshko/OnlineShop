package io.github.zhyshko.mapper.dto.user;

import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.model.user.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = UUID.class )
public interface AddressMapper {

    @Mapping(target = "externalId", expression = "java(UUID.randomUUID())")
    Address toModel(AddressData addressData);
    AddressData toDto(Address addressData);

}
