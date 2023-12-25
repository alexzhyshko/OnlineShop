package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dao.order.PaymentDao;
import io.github.zhyshko.dto.order.PaymentData;
import io.github.zhyshko.model.order.Payment;
import io.github.zhyshko.service.order.PaymentStatusService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PaymentEntryMapper.class})
public abstract class PaymentMapper {

    @Autowired
    protected PaymentDao paymentDao;
    @Autowired
    protected PaymentEntryMapper paymentEntryMapper;
    @Autowired
    protected PaymentStatusService paymentStatusService;

    public Payment toModel(PaymentData paymentData) {
        if ( paymentData == null ) {
            return null;
        }

        return paymentDao.findByExternalId(paymentData.getExternalId())
                .orElseGet(() -> paymentDao.save(createPayment(paymentData)));
    }

    @Mapping(target = "status", expression = "java(payment.getStatus().getName())")
    public abstract PaymentData toDto(Payment payment);

    private Payment createPayment(PaymentData paymentData) {
        Payment.PaymentBuilder<?, ?> payment = Payment.builder();

        payment.externalId( paymentData.getExternalId() );
        payment.status( paymentStatusService.getOrCreate(paymentData.getStatus()) );
        payment.amount( paymentData.getAmount() );
        payment.paymentEntries( paymentEntryMapper.toModelList( paymentData.getPaymentEntries() ) );

        return payment.build();
    }

}
