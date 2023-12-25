package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.facade.AccountFacade;
import io.github.zhyshko.mapper.dto.order.OrderMapper;
import io.github.zhyshko.mapper.dto.user.AddressMapper;
import io.github.zhyshko.mapper.dto.user.UserMapper;
import io.github.zhyshko.model.user.Address;
import io.github.zhyshko.model.user.User;
import io.github.zhyshko.service.order.OrderService;
import io.github.zhyshko.service.user.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultAccountFacade implements AccountFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserData getProfileInfo() {
        return userMapper.toDto(userService.getByExternalId(getCurrentUserExternalId()));
    }

    @Override
    public List<OrderData> getPastOrders() {
        return orderMapper.toDtoList(
                orderService.getUserOrders(getCurrentUserExternalId()));
    }

    @Override
    public void changePersonalData(UserData userData) {
        User existingUser = userService.getByExternalId(getCurrentUserExternalId());
        existingUser.setFirstName(userData.getFirstName());
        existingUser.setLastName(userData.getLastName());
        existingUser.setEmail(userData.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        userService.save(existingUser);
    }

    @Override
    public void changeAddress(AddressData addressData) {
        User existingUser = userService.getByExternalId(getCurrentUserExternalId());
        Address address = existingUser.getAddress();
        address.setCity(addressData.getCity());
        address.setRoom(addressData.getRoom());
        address.setStreetNumber(addressData.getStreetNumber());
        address.setStreetName(addressData.getStreetName());
        address.setStreetAdditive(addressData.getStreetAdditive());
        address.setAdditionalInfo(addressData.getAdditionalInfo());

        userService.save(existingUser);
    }

    private UUID getCurrentUserExternalId() {
        return userService.getCurrentUserExternalId().orElseThrow(() -> new RuntimeException("No user in session"));
    }
}
