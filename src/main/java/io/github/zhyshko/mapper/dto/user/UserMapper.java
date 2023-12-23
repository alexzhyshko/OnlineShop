package io.github.zhyshko.mapper.dto.user;

import io.github.zhyshko.dao.order.DeliveryModeDao;
import io.github.zhyshko.dao.user.UserDao;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.model.order.*;
import io.github.zhyshko.model.user.User;
import io.github.zhyshko.service.order.CartService;
import io.github.zhyshko.service.order.DeliveryModeService;
import io.github.zhyshko.service.order.PaymentModeService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public abstract class UserMapper {

    @Autowired
    protected UserDao userDao;
    @Autowired
    protected CartService cartService;

    public User toModel(UserData orderData) {
        if (orderData == null) {
            return null;
        }

        return userDao.findByEmail(orderData.getEmail())
                .orElseGet(() -> userDao.save(createUser(orderData)));
    }

    public abstract UserData toDto(User order);

    private User createUser(UserData userData) {
        return User.builder()
                .externalId(UUID.randomUUID())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .timeCreated(LocalDateTime.now())
                .password(userData.getPassword())
                .address(Mappers.getMapper(AddressMapper.class).toModel(userData.getAddress()))
                .cart(cartService.createCart())
                .build();
    }
}
