package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dto.order.PaymentData;
import io.github.zhyshko.dto.order.PaymentEntryData;
import io.github.zhyshko.model.order.Payment;
import io.github.zhyshko.model.order.PaymentEntry;
import io.github.zhyshko.service.order.PaymentStatusService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PaymentEntryMapper {

    @Autowired
    protected PaymentStatusService paymentStatusService;

    public PaymentEntry toModel(PaymentEntryData paymentEntryData) {
        if ( paymentEntryData == null ) {
            return null;
        }

        return createPaymentEntry(paymentEntryData);
    }

    public abstract List<PaymentEntry> toModelList(List<PaymentEntryData> paymentDataList);

    @Mapping(target = "status", expression = "java(payment.getStatus().getName())")
    public abstract PaymentEntryData toDto(PaymentEntry payment);

    private PaymentEntry createPaymentEntry(PaymentEntryData paymentEntryData) {
        PaymentEntry.PaymentEntryBuilder<?, ?> paymentEntry = PaymentEntry.builder();

        paymentEntry.status(paymentStatusService.getOrCreate(paymentEntryData.getStatus()));
        paymentEntry.amount(paymentEntryData.getAmount());
        paymentEntry.successful(paymentEntryData.isSuccessful());

        return paymentEntry.build();
    }

}
