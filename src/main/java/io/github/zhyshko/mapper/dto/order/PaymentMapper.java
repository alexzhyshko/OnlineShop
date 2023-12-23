package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dto.order.PaymentData;
import io.github.zhyshko.model.order.Payment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PaymentEntryMapper.class})
public abstract class PaymentMapper {

    public abstract Payment toModel(PaymentData paymentData);

    @Mapping(target = "status", expression = "java(payment.getStatus().getName())")
    public abstract PaymentData toDto(Payment payment);

}
