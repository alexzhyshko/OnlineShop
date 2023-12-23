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
public class ProductWsDto {

    private Long id;
    private UUID externalId;
    private StoreWsDto store;

    private List<ProductAttributeWsDto> productAttributes;
    private List<AuthorWsDto> authors;
    private List<PublisherWsDto> publishers;
    private List<CategoryWsDto> categories;

}
