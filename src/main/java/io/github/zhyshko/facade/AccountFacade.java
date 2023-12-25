package io.github.zhyshko.facade;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.dto.user.UserData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountFacade {
    UserData getProfileInfo();

    List<OrderData> getPastOrders();

    void changePersonalData(UserData userData);

    void changeAddress(AddressData addressData);
}
