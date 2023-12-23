package io.github.zhyshko.model.product;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.user.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name="Publisher")
@Table(name="publishers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Publisher extends Base {

    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String email;
    private String website;
    private String phone;

}
