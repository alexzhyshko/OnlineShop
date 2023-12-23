package io.github.zhyshko.model.order;

import io.github.zhyshko.model.Base;
import io.github.zhyshko.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderEntry> orderEntries;
    private LocalDateTime timeCreated;
    @ManyToOne(cascade = CascadeType.ALL)
    private OrderStatus orderStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private PaymentStatus paymentStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private PaymentMode paymentMode;
    @ManyToOne(cascade = CascadeType.ALL)
    private DeliveryMode deliveryMode;
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

}
