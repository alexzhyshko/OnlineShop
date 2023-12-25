package io.github.zhyshko.mapper.dto.user;

import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.model.user.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    Address toModel(AddressData addressData);
    AddressData toDto(Address addressData);

}
