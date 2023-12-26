package io.github.zhyshko.facade;

import io.github.zhyshko.dto.product.ProductData;

import java.util.UUID;

public interface ProductFacade {
    ProductData getProduct(UUID productExternalId);
}
