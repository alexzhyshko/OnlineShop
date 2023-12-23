package io.github.zhyshko.wsdto.user;

import io.github.zhyshko.wsdto.StoreWsDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class UserWsDto {

    private Long id;
    private UUID externalId;
    private StoreWsDto store;

}
