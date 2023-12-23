package io.github.zhyshko.model.order;

import io.github.zhyshko.model.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="PaymentMode")
@Table(name="payment_modes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PaymentMode extends Base {

    @Column(unique = true, nullable = false)
    private PaymentModeEnum name;

}
