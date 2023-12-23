package io.github.zhyshko.model.user;

import io.github.zhyshko.model.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="Address")
@Table(name="addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Address extends Base {

    private String city;
    private String streetName;
    private Integer streetNumber;
    private String streetAdditive;
    private Integer room;
    private String additionalInfo;

}
