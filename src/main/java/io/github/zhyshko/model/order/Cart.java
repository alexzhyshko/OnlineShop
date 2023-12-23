package io.github.zhyshko.model.order;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name="Cart")
@Table(name="carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cart extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private PaymentMode paymentMode;
    @ManyToOne(cascade = CascadeType.ALL)
    private DeliveryMode deliveryMode;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private List<CartEntry> cartEntryList;

}
