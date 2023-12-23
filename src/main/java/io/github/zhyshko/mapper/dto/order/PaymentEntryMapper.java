package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dto.order.PaymentData;
import io.github.zhyshko.dto.order.PaymentEntryData;
import io.github.zhyshko.model.order.Payment;
import io.github.zhyshko.model.order.PaymentEntry;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PaymentEntryMapper {

    public abstract PaymentEntry toModel(PaymentEntryData paymentData);

    @Mapping(target = "status", expression = "java(payment.getStatus().getName())")
    public abstract PaymentEntryData toDto(PaymentEntry payment);

}
