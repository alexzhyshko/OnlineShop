package io.github.zhyshko.facade.impl;

import io.github.zhyshko.client.RecommendationsClient;
import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.RecommendationsFacade;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.mapper.wsdto.order.OrderWsDtoMapper;
import io.github.zhyshko.service.order.OrderService;
import io.github.zhyshko.service.product.ProductService;
import io.github.zhyshko.service.store.StoreService;
import io.github.zhyshko.service.user.UserService;
import io.github.zhyshko.wsdto.recommmendations.RecommendationsRecord;
import io.github.zhyshko.wsdto.recommmendations.RecommendationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DefaultRecommendationsFacade implements RecommendationsFacade {

    @Autowired
    private RecommendationsClient recommendationsClient;
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderWsDtoMapper orderWsDtoMapper;

    @Override
    public List<RecommendationsResponse> getRecommendations() {
        UUID storeExternalId = storeService.getStoreExternalId();
        return userService.getCurrentUserExternalId()
                .map(uuid -> {
                    boolean userOrdersExist = orderService.userPaidOrdersExist(uuid);
                    if(userOrdersExist) {
                        return recommendationsClient.getPersonalizedRecommendations(storeExternalId, uuid);
                    }
                    return recommendationsClient.getGeneralRecommendations(storeExternalId);
                })
                .orElseGet(() -> recommendationsClient.getGeneralRecommendations(storeExternalId));
    }

    @Override
    public List<ProductData> getHomepageRecommendations() {
        return getRecommendations().stream()
                .sorted(Comparator.comparingLong(RecommendationsResponse::score).reversed())
                .map(r -> productService.get(r.externalId()))
                .filter(Optional::isPresent)
                .map(re -> productMapper.toDto(re.get()))
                .toList();
    }

    @Override
    public void sendOrder(OrderData orderData) {
        recommendationsClient.sendOrder(orderWsDtoMapper.toWsDto(orderData));
    }

    @Override
    public void sendReview(OrderData orderData) {
        recommendationsClient.sendReview(orderWsDtoMapper.toWsDto(orderData));
    }
}
