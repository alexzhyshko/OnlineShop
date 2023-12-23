package io.github.zhyshko.service.product;

import io.github.zhyshko.model.product.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product saveOrUpdate(Product product);

    Product getOrCreate(Product product);

    Optional<Product> get(UUID externalId);
}
