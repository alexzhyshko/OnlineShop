package io.github.zhyshko.service.product;

import io.github.zhyshko.model.product.ProductType;
import io.github.zhyshko.model.product.ProductTypeEnum;

public interface ProductTypeService {

    ProductType getOrCreate(ProductTypeEnum productTypeEnum);

}
