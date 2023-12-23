package io.github.zhyshko.service.product.impl;

import io.github.zhyshko.dao.product.ProductTypeDao;
import io.github.zhyshko.model.order.DeliveryMode;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.product.ProductType;
import io.github.zhyshko.model.product.ProductTypeEnum;
import io.github.zhyshko.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultProductTypeService implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public ProductType getOrCreate(ProductTypeEnum productTypeEnum) {
        return productTypeDao.findByName(productTypeEnum).orElseGet(() -> createProductType(productTypeEnum));
    }

    private ProductType createProductType(ProductTypeEnum productTypeEnum) {
        return productTypeDao.save(ProductType.builder()
                .externalId(UUID.randomUUID())
                .name(productTypeEnum)
                .build());
    }

}
