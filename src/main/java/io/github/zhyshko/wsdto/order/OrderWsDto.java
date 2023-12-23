package io.github.zhyshko.wsdto.order;

import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.user.UserWsDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class OrderWsDto {

    private Long id;
    private UUID externalId;
    private StoreWsDto store;

    private UserWsDto owner;
    private List<OrderEntryWsDto> orderEntries;
    private LocalDateTime timeCreated;

}
