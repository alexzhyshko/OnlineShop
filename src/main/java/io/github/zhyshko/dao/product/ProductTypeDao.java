package io.github.zhyshko.dao.product;

import io.github.zhyshko.model.product.ProductType;
import io.github.zhyshko.model.product.ProductTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductTypeDao extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findByName(ProductTypeEnum productTypeEnum);

}
