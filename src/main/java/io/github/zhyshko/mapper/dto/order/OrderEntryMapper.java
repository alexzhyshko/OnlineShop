package io.github.zhyshko.mapper.dto.order;

import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.mapper.dto.review.ReviewEntryMapper;
import io.github.zhyshko.model.order.OrderEntry;
import io.github.zhyshko.service.order.OrderEntryService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ProductMapper.class, ReviewEntryMapper.class})
public abstract class OrderEntryMapper {

    @Autowired
    protected ReviewEntryMapper reviewEntryMapper;
    @Autowired
    protected ProductMapper productMapper;
    @Autowired
    protected OrderEntryService orderEntryService;

    public OrderEntry toModel(OrderEntryData orderEntryData) {
        if (orderEntryData == null) {
            return null;
        }

        return orderEntryService.saveOrUpdate(createOrderEntry(orderEntryData));
    }

    public abstract OrderEntryData toDto(OrderEntry order);

    public List<OrderEntryData> toDtoList(List<OrderEntry> entries) {
        return entries.stream().map(this::toDto).toList();
    }

    private OrderEntry createOrderEntry(OrderEntryData orderEntryData) {
        OrderEntry.OrderEntryBuilder<?, ?> orderEntry = OrderEntry.builder();

        orderEntry.externalId(orderEntryData.getExternalId());
        orderEntry.reviewEntry(reviewEntryMapper.toModel(orderEntryData.getReviewEntry()));
        orderEntry.product(productMapper.toModel(orderEntryData.getProduct()));
        orderEntry.timeCreated(orderEntryData.getTimeCreated());
        orderEntry.amount(orderEntryData.getAmount());
        orderEntry.quantity(orderEntryData.getQuantity());

        return orderEntry.build();
    }

}
