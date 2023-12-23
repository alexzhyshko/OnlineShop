package io.github.zhyshko.dto.product;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class CategoryData {

    private UUID externalId;
    private List<CategoryData> subcategories;
    private String name;

}
