package io.github.zhyshko.controller;

import io.github.zhyshko.dto.order.CartEntryData;
import io.github.zhyshko.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartFacade cartFacade;

    @PostMapping("/add")
    public void addToCart(@RequestParam UUID productExternalId,
                          @RequestParam Integer quantity) {
        cartFacade.addToCart(productExternalId, quantity);
    }

    @PatchMapping("/update")
    public void updateCart(@RequestParam UUID productExternalId,
                          @RequestParam Integer quantity) {
        cartFacade.changeCount(productExternalId, quantity);
    }

    @DeleteMapping("/delete")
    public void deleteFromCart(@RequestParam UUID productExternalId) {
        cartFacade.removeEntry(productExternalId);
    }

}
