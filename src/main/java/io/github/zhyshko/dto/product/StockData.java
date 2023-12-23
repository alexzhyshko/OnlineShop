package io.github.zhyshko.dto.product;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class StockData {

    private Integer available;
    private Integer reserved;
    private ProductData product;
    private WarehouseData warehouse;

}
