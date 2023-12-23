package io.github.zhyshko.model.product;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.user.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="Warehouse")
@Table(name="warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Warehouse extends Base {

    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
