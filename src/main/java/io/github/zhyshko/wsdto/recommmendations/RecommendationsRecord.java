package io.github.zhyshko.wsdto.recommmendations;

import io.github.zhyshko.dto.product.ProductData;

public record RecommendationsRecord(ProductData productData, Long score) {
}
