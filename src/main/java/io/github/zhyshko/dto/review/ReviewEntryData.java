package io.github.zhyshko.dto.review;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ReviewEntryData {

    private UUID externalId;

    private LocalDateTime timeCreated;
    private Integer mark;

}
