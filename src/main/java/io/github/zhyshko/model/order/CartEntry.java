package io.github.zhyshko.model.order;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name="CartEntry")
@Table(name="cart_entries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CartEntry extends Base {

    private LocalDateTime timeCreated;
    private Double amount;
    private Integer quantity;
    @ManyToOne
    private Product product;

}
