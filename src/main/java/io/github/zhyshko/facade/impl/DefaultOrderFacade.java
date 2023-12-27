package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.order.*;
import io.github.zhyshko.dto.review.ReviewEntryData;
import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.facade.MailFacade;
import io.github.zhyshko.facade.OrderFacade;
import io.github.zhyshko.facade.RecommendationsFacade;
import io.github.zhyshko.mapper.dto.order.CartMapper;
import io.github.zhyshko.mapper.dto.order.OrderMapper;
import io.github.zhyshko.mapper.dto.user.UserMapper;
import io.github.zhyshko.model.order.*;
import io.github.zhyshko.service.order.CartService;
import io.github.zhyshko.service.order.OrderService;
import io.github.zhyshko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecommendationsFacade recommendationsFacade;
    @Autowired
    private MailFacade mailFacade;

    @Override
    public OrderData createOrder(CartData cartData) {
        UserData currentUser = getCurrentUser();
        Cart currentCart = cartService.getCurrentCart();
        CartData currentCartData = cartMapper.toDto(cartService.getCurrentCart());
        OrderData orderData = OrderData.builder()
                .externalId(UUID.randomUUID())
                .orderStatus(cartData.getPaymentMode().equals(PaymentModeEnum.CASH) ? OrderStatusEnum.CREATED : OrderStatusEnum.IN_PROGRESS)
                .paymentStatus(cartData.getPaymentMode().equals(PaymentModeEnum.CASH) ? PaymentStatusEnum.NOT_PAID : PaymentStatusEnum.PAID)
                .paymentMode(cartData.getPaymentMode())
                .deliveryMode(cartData.getDeliveryMode())
                .address(cartData.getAddress() == null ? currentUser.getAddress() : cartData.getAddress())
                .timeCreated(LocalDateTime.now())
                .owner(currentUser)
                .payment(createEmptyPayment(currentCartData))
                .orderEntries(createOrderEntries(currentCartData))
                .build();

        orderService.save(orderMapper.toModel(orderData));
        currentCart.setCartEntryList(Collections.emptyList());
        cartService.save(currentCart);

        if(cartData.getPaymentMode().equals(PaymentModeEnum.CASH)) {
            recommendationsFacade.sendOrder(orderData);
            mailFacade.sendReviewEmail(orderData);
        }

        return orderData;
    }

    @Override
    public void payOrder(UUID orderExternalId) {
        OrderData orderData = orderMapper.toDto(orderService.getByExternalId(orderExternalId));
        orderData.setPaymentStatus(PaymentStatusEnum.PAID);
        orderData.setOrderStatus(OrderStatusEnum.IN_PROGRESS);
        PaymentData paymentData = orderData.getPayment();
        paymentData.setStatus(PaymentStatusEnum.PAID);
        paymentData.getPaymentEntries().add(createSuccessfulPaymentEntry(paymentData));

        orderService.save(orderMapper.toModel(orderData));

        recommendationsFacade.sendOrder(orderData);
        mailFacade.sendReviewEmail(orderData);
    }

    @Override
    public OrderData getOrder(UUID orderExternalId) {
        return orderMapper.toDto(orderService.getByExternalId(orderExternalId));
    }

    @Override
    public void addReview(OrderData orderData) {
        OrderData existingOrderData = orderMapper.toDto(orderService.getByExternalId(orderData.getExternalId()));
        Map<UUID, ReviewEntryData> existingReviewEntries = existingOrderData.getOrderEntries().stream()
                .map(OrderEntryData::getReviewEntry)
                .collect(Collectors.toMap(ReviewEntryData::getExternalId, Function.identity()));

        orderData.getOrderEntries().stream().map(OrderEntryData::getReviewEntry).forEach(it -> {
            existingReviewEntries.get(it.getExternalId()).setMark(it.getMark());
            existingReviewEntries.get(it.getExternalId()).setText(it.getText());
        });

        orderService.save(orderMapper.toModel(existingOrderData));

        recommendationsFacade.sendReview(orderData);
    }

    private PaymentEntryData createSuccessfulPaymentEntry(PaymentData paymentData) {
        return PaymentEntryData.builder()
                .amount(paymentData.getAmount())
                .successful(true)
                .status(PaymentStatusEnum.PAID)
                .build();
    }

    private UserData getCurrentUser() {
        return userMapper.toDto(userService.getByExternalId(userService.getCurrentUserExternalId()
                .orElseThrow(() -> new RuntimeException("No user in session"))));
    }

    private PaymentData createEmptyPayment(CartData cartData) {
        Double amount = cartData.getCartEntryList().stream().mapToDouble(CartEntryData::getAmount).sum();
        return PaymentData.builder()
                .amount(amount)
                .status(PaymentStatusEnum.NOT_PAID)
                .externalId(UUID.randomUUID())
                .build();
    }

    private List<OrderEntryData> createOrderEntries(CartData cartData) {
        return cartData.getCartEntryList().stream()
                .map(this::mapToCartEntry)
                .toList();
    }

    private OrderEntryData mapToCartEntry(CartEntryData cartEntryData) {
        return OrderEntryData.builder()
                .timeCreated(LocalDateTime.now())
                .externalId(UUID.randomUUID())
                .amount(cartEntryData.getAmount())
                .quantity(cartEntryData.getQuantity())
                .product(cartEntryData.getProduct())
                .reviewEntry(createNeutralReview())
                .build();
    }

    private ReviewEntryData createNeutralReview() {
        return ReviewEntryData.builder()
                .timeCreated(LocalDateTime.now())
                .mark(0)
                .externalId(UUID.randomUUID())
                .build();
    }
}
