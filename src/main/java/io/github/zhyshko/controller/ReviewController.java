package io.github.zhyshko.controller;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.facade.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private OrderFacade orderFacade;

    @GetMapping("/form")
    public OrderData getOrder(@RequestParam UUID orderExternalId) {
        return orderFacade.getOrder(orderExternalId);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody OrderData orderData) {
        orderFacade.addReview(orderData);
    }

}
