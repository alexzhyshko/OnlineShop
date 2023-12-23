package io.github.zhyshko.dto.order;

import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.order.PaymentModeEnum;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CartData {

    private PaymentModeEnum paymentMode;
    private DeliveryModeEnum deliveryMode;
    private List<CartEntryData> cartEntryList;
    private UUID externalId;

}
