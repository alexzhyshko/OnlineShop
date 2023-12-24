package io.github.zhyshko;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.dto.product.*;
import io.github.zhyshko.dto.review.ReviewEntryData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.order.OrderStatusEnum;
import io.github.zhyshko.model.order.PaymentModeEnum;
import io.github.zhyshko.model.order.PaymentStatusEnum;
import io.github.zhyshko.model.product.ProductTypeEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DatabasePopulator {

    private static final UUID PRODUCT_1_ID = UUID.fromString("31224ee2-3d49-4a6a-ba73-624ef0eab9b4");
    private static final UUID PRODUCT_2_ID = UUID.fromString("d8678259-9d59-44ce-b96d-2a5c024cab32");
    private static final UUID PRODUCT_3_ID = UUID.fromString("afb19e29-bca8-4b44-b6b7-c4928a249980");

    private static final UUID USER_1_ID = UUID.fromString("d8678259-9d59-44ce-b96d-2a5c024cab32");
    private static final UUID USER_2_ID = UUID.fromString("afb19e29-bca8-4b44-b6b7-c4928a249980");
    private static final UUID SUBCATEGORY1_ID = UUID.randomUUID();
    private static final UUID SUBCATEGORY2_ID = UUID.randomUUID();
    private static final UUID CATEGORY_ID = UUID.randomUUID();

    public void populateDatabase() {

        //OrderData orderData1 = createOrder(List.of(createOrderEntry1(1), createOrderEntry2(1)), createUserData1());
        //OrderData orderData2 = createOrder(List.of(createOrderEntry3(-1), createOrderEntry1(1)), createUserData1());
        //OrderData orderData3 = createOrder(List.of(createOrderEntry1(1)), createUserData2());

    }

    public OrderData getOrder() {
        return createOrder(List.of(createOrderEntry1(1), createOrderEntry2(1), createOrderEntry3(1)), createUserData1());
    }

    private OrderData createOrder(List<OrderEntryData> orderEntryDataList, UserData userData) {
        return OrderData.builder()
                .externalId(UUID.randomUUID())
                .owner(userData)
                .orderEntries(orderEntryDataList)
                .timeCreated(LocalDateTime.now())
                .paymentStatus(PaymentStatusEnum.NOT_PAID)
                .deliveryMode(DeliveryModeEnum.HOME)
                .paymentMode(PaymentModeEnum.CASH)
                .orderStatus(OrderStatusEnum.CREATED)
                .build();
    }

    private UserData createUserData1() {
        return UserData.builder()
                .externalId(USER_1_ID)
                .build();
    }

    private UserData createUserData2() {
        return UserData.builder()
                .externalId(USER_2_ID)
                .build();
    }

    private OrderEntryData createOrderEntry1(int mark) {
        return OrderEntryData.builder()
                .externalId(UUID.randomUUID())
                .timeCreated(LocalDateTime.now())
                .product(createProduct1())
                .reviewEntry(createReviewEntry(mark))
                .build();
    }

    private OrderEntryData createOrderEntry2(int mark) {
        return OrderEntryData.builder()
                .externalId(UUID.randomUUID())
                .timeCreated(LocalDateTime.now())
                .product(createProduct2())
                .reviewEntry(createReviewEntry(mark))
                .build();
    }

    private OrderEntryData createOrderEntry3(int mark) {
        return OrderEntryData.builder()
                .externalId(UUID.randomUUID())
                .timeCreated(LocalDateTime.now())
                .product(createProduct3())
                .reviewEntry(createReviewEntry(mark))
                .build();
    }

    private ReviewEntryData createReviewEntry(int mark) {
        return ReviewEntryData.builder()
                .externalId(UUID.randomUUID())
                .mark(mark)
                .timeCreated(LocalDateTime.now())
                .build();
    }

    private ProductData createProduct1() {
        return ProductData.builder()
                .externalId(PRODUCT_1_ID)
                .productAttributes(List.of(createProductAttributeData()))
                .authors(List.of(createAuthorData()))
                .publishers(List.of(createPublisherData()))
                .categories(List.of(createSubcategory2Data()))
                .name("aadadad")
                .type(ProductTypeEnum.PHYSICAL)
                .price(10.99d)
                .build();
    }

    private ProductData createProduct2() {
        return ProductData.builder()
                .externalId(PRODUCT_2_ID)
                .productAttributes(List.of(createProductAttributeData()))
                .authors(List.of(createAuthorData()))
                .publishers(List.of(createPublisherData()))
                .categories(List.of(createSubcategory1Data()))
                .name("aadadad2")
                .type(ProductTypeEnum.PHYSICAL)
                .price(1.99d)
                .build();
    }

    private ProductData createProduct3() {
        return ProductData.builder()
                .externalId(PRODUCT_3_ID)
                .productAttributes(List.of(createProductAttributeData()))
                .authors(List.of(createAuthorData()))
                .publishers(List.of(createPublisherData()))
                .categories(List.of(createCategoryData()))
                .name("aadadad3")
                .type(ProductTypeEnum.PHYSICAL)
                .price(3.99d)
                .build();
    }

    private CategoryData createCategoryData() {
        return CategoryData.builder()
                .externalId(CATEGORY_ID)
                .subcategories(List.of(createSubcategory1Data()))
                .name("Category")
                .build();
    }

    private CategoryData createSubcategory1Data() {
        return CategoryData.builder()
                .externalId(SUBCATEGORY1_ID)
                .subcategories(List.of(createSubcategory2Data()))
                .name("Subcategory 1")
                .build();
    }

    private CategoryData createSubcategory2Data() {
        return CategoryData.builder()
                .externalId(SUBCATEGORY2_ID)
                .name("Subcategory 2")
                .build();
    }

    private PublisherData createPublisherData() {
        return PublisherData.builder()
                .externalId(UUID.randomUUID())
                .build();
    }

    private AuthorData createAuthorData() {
        return AuthorData.builder()
                .externalId(UUID.randomUUID())
                .build();
    }

    private ProductAttributeData createProductAttributeData() {
        return ProductAttributeData.builder()
                .externalId(UUID.randomUUID())
                .build();
    }
}
