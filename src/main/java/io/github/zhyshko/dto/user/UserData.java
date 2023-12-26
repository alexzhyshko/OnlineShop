package io.github.zhyshko.dto.user;

import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.model.order.Cart;
import io.github.zhyshko.model.user.Address;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class UserData {

    private UUID externalId;

    private CartData cart;

    private LocalDateTime timeCreated;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String token;
    private AddressData address;
}
