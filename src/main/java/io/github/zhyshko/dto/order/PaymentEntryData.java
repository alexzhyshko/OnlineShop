package io.github.zhyshko.dto.order;

import io.github.zhyshko.model.order.PaymentStatusEnum;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class PaymentEntryData {

    private PaymentStatusEnum status;
    private Double amount;
    private boolean successful;

}
