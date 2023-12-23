package io.github.zhyshko.dto.order;

import io.github.zhyshko.model.order.PaymentStatusEnum;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class PaymentData {

    private UUID externalId;
    private PaymentStatusEnum status;
    private Double amount;
    private List<PaymentEntryData> paymentEntries;

}
