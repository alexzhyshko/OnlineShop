package io.github.zhyshko.dto.product;

import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.user.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class WarehouseData {

    private String name;
    private AddressData address;

}
