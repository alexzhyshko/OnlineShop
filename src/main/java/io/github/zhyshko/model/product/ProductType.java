package io.github.zhyshko.model.product;

import io.github.zhyshko.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="ProductType")
@Table(name="product_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductType extends Base {

    @Column(unique = true, nullable = false)
    private ProductTypeEnum name;

}
