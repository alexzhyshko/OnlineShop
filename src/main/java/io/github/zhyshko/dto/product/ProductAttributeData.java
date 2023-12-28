package io.github.zhyshko.dto.product;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProductAttributeData {

    private UUID externalId;
    private String value;

}
