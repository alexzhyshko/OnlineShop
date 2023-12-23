package io.github.zhyshko.mapper.dto.user;

import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.model.user.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address toModel(AddressData addressData);
    AddressData toDto(Address addressData);

}
