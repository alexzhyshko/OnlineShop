package io.github.zhyshko.controller;

import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping
    public OrderData checkout(@RequestBody CartData cartData) {
        return orderFacade.createOrder(cartData);
    }

    @GetMapping("/payment")
    public ResponseEntity<String> payment(@RequestParam UUID order, @RequestParam String cardNumber, @RequestParam String date, @RequestParam String cvv) {
        boolean valid = cardNumber.equals("4411 4444 4444 4444") && date.equals("08/29") && cvv.equals("123");

        if(valid) {
            orderFacade.payOrder(order);
        }

        return ResponseEntity
                .status(valid ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST)
                .body(valid ? "OK" : "NOK");
    }

}
