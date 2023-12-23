package io.github.zhyshko.dto.product;

import io.github.zhyshko.model.product.ProductType;
import io.github.zhyshko.model.product.ProductTypeEnum;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProductData {

    private UUID externalId;

    private List<ProductAttributeData> productAttributes;
    private List<AuthorData> authors;
    private List<PublisherData> publishers;
    private List<CategoryData> categories;
    private byte[] image;
    private Double price;
    private ProductTypeEnum type;
    private String name;

}
