package io.github.zhyshko.dto.order;

import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.dto.review.ReviewEntryData;
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
public class OrderEntryData {

    private UUID externalId;
    private LocalDateTime timeCreated;
    private ReviewEntryData reviewEntry;
    private ProductData product;
    private Double amount;
    private Integer quantity;


}
