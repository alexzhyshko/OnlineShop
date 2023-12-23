package io.github.zhyshko.dto.order;

import io.github.zhyshko.dto.product.ProductData;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CartEntryData {

    private UUID externalId;
    private LocalDateTime timeCreated;
    private Double amount;
    private Integer quantity;
    private ProductData product;

}
