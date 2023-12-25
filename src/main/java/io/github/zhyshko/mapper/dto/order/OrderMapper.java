package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dao.order.OrderDao;
import io.github.zhyshko.dao.order.PaymentStatusDao;
import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.mapper.dto.user.AddressMapper;
import io.github.zhyshko.mapper.dto.user.UserMapper;
import io.github.zhyshko.model.order.Order;
import io.github.zhyshko.model.order.OrderEntry;
import io.github.zhyshko.service.order.DeliveryModeService;
import io.github.zhyshko.service.order.OrderStatusService;
import io.github.zhyshko.service.order.PaymentModeService;
import io.github.zhyshko.service.order.PaymentStatusService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, OrderEntryMapper.class, PaymentMapper.class})
public abstract class OrderMapper {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected OrderEntryMapper orderEntryMapper;
    @Autowired
    protected OrderDao orderDao;
    @Autowired
    protected OrderStatusService orderStatusService;
    @Autowired
    protected PaymentStatusService paymentStatusService;
    @Autowired
    protected DeliveryModeService deliveryModeService;
    @Autowired
    protected PaymentModeService paymentModeService;
    @Autowired
    protected PaymentMapper paymentMapper;
    @Autowired
    protected AddressMapper addressMapper;

    public Order toModel(OrderData orderData) {
        if (orderData == null) {
            return null;
        }

        return orderDao.findByExternalId(orderData.getExternalId())
                .orElseGet(() -> orderDao.save(createOrder(orderData)));

    }

    @Mapping(target = "paymentMode", expression = "java(order.getPaymentMode().getName())")
    @Mapping(target = "deliveryMode", expression = "java(order.getDeliveryMode().getName())")
    @Mapping(target = "paymentStatus", expression = "java(order.getPaymentStatus().getName())")
    @Mapping(target = "orderStatus", expression = "java(order.getOrderStatus().getName())")
    public abstract OrderData toDto(Order order);

    public List<OrderData> toDtoList(List<Order> orders) {
        return orders.stream().map(this::toDto).toList();
    }

    private Order createOrder(OrderData orderData) {
        Order.OrderBuilder<?, ?> order = Order.builder();

        order.externalId(orderData.getExternalId());
        order.owner(userMapper.toModel(orderData.getOwner()));
        order.orderEntries(createOrderEntryDataList(orderData.getOrderEntries()));
        order.timeCreated(orderData.getTimeCreated());
        order.orderStatus(orderStatusService.getOrCreate(orderData.getOrderStatus()));
        order.paymentStatus(paymentStatusService.getOrCreate(orderData.getPaymentStatus()));
        order.deliveryMode(deliveryModeService.getOrCreate(orderData.getDeliveryMode()));
        order.paymentMode(paymentModeService.getOrCreate(orderData.getPaymentMode()));
        order.payment(paymentMapper.toModel(orderData.getPayment()));
        order.address(addressMapper.toModel(orderData.getAddress()));

        return order.build();
    }

    protected List<OrderEntry> createOrderEntryDataList(List<OrderEntryData> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderEntry> list1 = new ArrayList<OrderEntry>( list.size() );
        for ( OrderEntryData orderEntryData : list ) {
            list1.add( orderEntryMapper.toModel( orderEntryData ) );
        }

        return list1;
    }

}
