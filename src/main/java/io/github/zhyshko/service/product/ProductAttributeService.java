package io.github.zhyshko.service.product;

import io.github.zhyshko.model.product.ProductAttribute;

import java.util.List;
import java.util.UUID;

public interface ProductAttributeService {

    ProductAttribute getOrCreate(ProductAttribute productAttribute);
}
