package io.github.zhyshko.wsdto.order;

import io.github.zhyshko.wsdto.StoreWsDto;
import io.github.zhyshko.wsdto.product.ProductWsDto;
import io.github.zhyshko.wsdto.review.ReviewEntryWsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class OrderEntryWsDto {

    private Long id;
    private UUID externalId;
    private StoreWsDto store;

    private LocalDateTime timeCreated;
    private ReviewEntryWsDto reviewEntry;
    private ProductWsDto product;

}
