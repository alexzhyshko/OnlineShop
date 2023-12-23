package io.github.zhyshko.model.product;

import io.github.zhyshko.model.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name="Author")
@Table(name="authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Author extends Base {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
