package io.github.zhyshko.wsdto.review;

import io.github.zhyshko.wsdto.StoreWsDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ReviewEntryWsDto {

    private Long id;
    private UUID externalId;
    private StoreWsDto store;

    private LocalDateTime timeCreated;
    private Integer mark;

}
