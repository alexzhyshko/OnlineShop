package io.github.zhyshko.dto.product;

import io.github.zhyshko.dto.user.AddressData;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class PublisherData {

    private UUID externalId;
    private String name;
    private AddressData address;
    private String email;
    private String website;
    private String phone;

}
