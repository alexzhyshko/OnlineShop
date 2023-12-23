package io.github.zhyshko.wsdto.product;

import io.github.zhyshko.wsdto.StoreWsDto;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class CategoryWsDto {

    private Long id;
    private UUID externalId;
    private List<CategoryWsDto> subcategories;
    private StoreWsDto store;

}
