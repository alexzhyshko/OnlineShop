package io.github.zhyshko.dto.product;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class AuthorData {

    private UUID externalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
