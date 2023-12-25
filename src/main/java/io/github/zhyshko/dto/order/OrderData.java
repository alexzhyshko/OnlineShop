package io.github.zhyshko.dto.order;

import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.model.order.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class OrderData {

    private UUID externalId;
    private UserData owner;
    private List<OrderEntryData> orderEntries;
    private LocalDateTime timeCreated;
    private OrderStatusEnum orderStatus;
    private PaymentStatusEnum paymentStatus;
    private PaymentModeEnum paymentMode;
    private DeliveryModeEnum deliveryMode;
    private PaymentData payment;
    private AddressData address;

}
