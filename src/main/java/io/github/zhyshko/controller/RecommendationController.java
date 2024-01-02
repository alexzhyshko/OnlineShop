package io.github.zhyshko.controller;

import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.RecommendationsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationsFacade recommendationsFacade;


    @GetMapping
    public List<ProductData> getRecommendations() {
        return recommendationsFacade.getHomepageRecommendations().stream().limit(4).toList();
    }

}
