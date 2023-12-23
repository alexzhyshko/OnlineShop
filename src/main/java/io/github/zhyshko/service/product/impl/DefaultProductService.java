package io.github.zhyshko.service.product.impl;

import io.github.zhyshko.dao.product.ProductDao;
import io.github.zhyshko.model.product.Product;
import io.github.zhyshko.service.product.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultProductService implements ProductService {

    private ProductDao productDao;

    public DefaultProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productDao.findByExternalId(product.getExternalId())
                .map(p -> {
                    product.setId(p.getId());
                    return productDao.save(product);
                })
                .orElseGet(() -> productDao.save(product));
    }

    @Override
    public Product getOrCreate(Product product) {
        return productDao.findByExternalId(product.getExternalId())
                .orElseGet(() -> productDao.save(product));
    }

    @Override
    public Optional<Product> get(UUID externalId) {
        return productDao.findByExternalId(externalId);
    }


}
