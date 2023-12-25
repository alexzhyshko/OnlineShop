package io.github.zhyshko.controller;

import feign.Response;
import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.user.AddressData;
import io.github.zhyshko.dto.user.UserData;
import io.github.zhyshko.facade.AccountFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountFacade accountFacade;

    @GetMapping("/profile")
    public UserData getProfile() {
        return accountFacade.getProfileInfo();
    }

    @GetMapping("/orders")
    public List<OrderData> getOrders() {
        return accountFacade.getPastOrders();
    }

    @PutMapping("/change")
    public ResponseEntity<String> changePersonalData(@RequestBody UserData userData) {
        accountFacade.changePersonalData(userData);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PutMapping("/address")
    public ResponseEntity<String> changeAddress(@RequestBody AddressData addressData) {
        accountFacade.changeAddress(addressData);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
