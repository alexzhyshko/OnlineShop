package io.github.zhyshko.controller;

import io.github.zhyshko.dto.order.CartData;
import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.CartFacade;
import io.github.zhyshko.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    public ProductData getProduct(@RequestParam UUID productExternalId) {
        return productFacade.getProduct(productExternalId);
    }

}
