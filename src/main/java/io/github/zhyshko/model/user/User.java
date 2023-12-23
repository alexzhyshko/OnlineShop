package io.github.zhyshko.model.user;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.order.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name="User")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends Base {

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    private LocalDateTime timeCreated;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
