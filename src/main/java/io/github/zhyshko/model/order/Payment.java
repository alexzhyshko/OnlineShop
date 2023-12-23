package io.github.zhyshko.model.order;

import io.github.zhyshko.model.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name = "Payment")
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment extends Base {

    @ManyToOne(cascade = CascadeType.ALL)
    private PaymentStatus status;
    private Double amount;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private List<PaymentEntry> paymentEntries;

}
