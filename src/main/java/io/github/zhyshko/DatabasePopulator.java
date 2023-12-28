package io.github.zhyshko;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.dto.product.*;
import io.github.zhyshko.dto.review.ReviewEntryData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.mapper.dto.product.CategoryMapper;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.model.order.DeliveryModeEnum;
import io.github.zhyshko.model.order.OrderStatusEnum;
import io.github.zhyshko.model.order.PaymentModeEnum;
import io.github.zhyshko.model.order.PaymentStatusEnum;
import io.github.zhyshko.model.product.Category;
import io.github.zhyshko.model.product.ProductAttribute;
import io.github.zhyshko.model.product.ProductTypeEnum;
import io.github.zhyshko.service.product.CategoryService;
import io.github.zhyshko.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class DatabasePopulator {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;


    public void populateDatabase() {

        CategoryData b = createCategory("Бойовики", Collections.emptyList());
        CategoryData p = createCategory("П'єси", Collections.emptyList());
        CategoryData h = createCategory("Художні", List.of(b, p));

        categoryMapper.toModel(b);
        categoryMapper.toModel(p);
        categoryMapper.toModel(h);

        productService.saveOrUpdate(productMapper.toModel(createProduct("Крос у небуття", 126d, List.of("Юрій Сорока"),
                List.of("Фоліо"), List.of("Українська", "Поліцейський детектив"), b, "https://static.yakaboo.ua/media/catalog/product/i/m/img_101685.jpg",
                "978-966-03-9545-9")));

        productService.saveOrUpdate(productMapper.toModel(createProduct("The Other People", 456d, List.of("С. Дж. Тюдор"),
                List.of("Penguin","Книжковий клуб \"Клуб Сімейного Дозвілля\""), List.of("Англійська","Психологічний трилер","Кримінальний трилер"), b, "https://static.yakaboo.ua/media/catalog/product/9/7/9780241371282.jpg",
                "9781405939621")));

        productService.saveOrUpdate(productMapper.toModel(createProduct("Характерник", 245d, List.of("Василь Шкляр"),
                List.of("Книжковий клуб \"Клуб Сімейного Дозвілля\""), List.of("Українська","Історична проза"), b, "https://static.yakaboo.ua/media/catalog/product/h/a/harakternik_front.jpg",
                "978-617-12-6841-8")));

        productService.saveOrUpdate(productMapper.toModel(createProduct("Command and Control", 1396d, List.of("Марк Кемерон"),
                List.of("Little, Brown Book Group","Sphere"), List.of("Англійська","Сучасна проза","Шпигуни і Політики"), b, "https://static.yakaboo.ua/media/catalog/product/7/1/71cl5i0rrml._sl1500_.jpg",
                "9781408727843")));

        productService.saveOrUpdate(productMapper.toModel(createProduct("Gideon's Corpse", 625d, List.of("Лінкольн Чайлд"," Дуглас Престон"),
                List.of("Orion"), List.of("Сучасна проза","Англійська","Психологічний трилер"), b, "https://static.yakaboo.ua/media/catalog/product/7/1/714sp5kuexl.jpg",
                "9781398718807")));
    }

    private ProductData createProduct(String name, double price, List<String> authors, List<String> publishers
            , List<String> productAttributes, CategoryData category, String imageLink, String isbn) {
        return ProductData.builder()
                .name(name)
                .isbn(isbn)
                .type(ProductTypeEnum.DIGITAL)
                .price(price)
                .externalId(UUID.randomUUID())
                .imageLink(imageLink)
                .categories(List.of(category))
                .authors(createAuthors(authors))
                .publishers(createPublishers(publishers))
                .productAttributes(createAttributes(productAttributes))
                .build();
    }

    private List<ProductAttributeData> createAttributes(List<String> productAttributes) {
        return productAttributes.stream().map(s -> createProductAttribute(s)).toList();
    }

    private List<PublisherData> createPublishers(List<String> publishers) {
        return publishers.stream().map(s -> createPublisher(s)).toList();
    }

    private List<AuthorData> createAuthors(List<String> authors) {
        return authors.stream().map(s -> createAuthor(s)).toList();
    }

    private CategoryData createCategory(String name, List<CategoryData> subcategories){
        return CategoryData.builder()
                .externalId(UUID.randomUUID())
                .name(name)
                .subcategories(subcategories)
                .build();
    }

    private AuthorData createAuthor(String name) {
        return AuthorData.builder()
                .externalId(UUID.randomUUID())
                .firstName(name)
                .build();
    }

    private PublisherData createPublisher(String name) {
        return PublisherData.builder()
                .externalId(UUID.randomUUID())
                .name(name)
                .build();
    }

    private ProductAttributeData createProductAttribute(String value) {
        return ProductAttributeData.builder()
                .externalId(UUID.randomUUID())
                .value(value)
                .build();
    }

}
