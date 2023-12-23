package io.github.zhyshko.model.product;

import io.github.zhyshko.model.Base;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="Stock")
@Table(name="stocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Stock extends Base {

    private Integer available;
    private Integer reserved;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    private Warehouse warehouse;

}
