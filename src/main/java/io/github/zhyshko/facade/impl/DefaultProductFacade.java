package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.ProductFacade;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultProductFacade implements ProductFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductData getProduct(UUID productExternalId) {
        return productMapper.toDto(productService.get(productExternalId).orElseThrow(() -> new RuntimeException("Product not found")));
    }
}
