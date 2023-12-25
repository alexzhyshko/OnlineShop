package io.github.zhyshko.facade;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.wsdto.recommmendations.RecommendationsResponse;

import java.util.List;

public interface RecommendationsFacade {

    List<ProductData> getHomepageRecommendations();

    List<RecommendationsResponse> getRecommendations();

    void sendOrder(OrderData orderData);

    void sendReview(OrderData orderData);
}
