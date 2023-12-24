package io.github.zhyshko.client;

import io.github.zhyshko.wsdto.recommmendations.RecommendationsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "RecommendationsClient", url = "${recommendation.service.url}")
public interface RecommendationsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/recommendations")
    List<RecommendationsResponse> getGeneralRecommendations(@RequestParam UUID storeId);

    @RequestMapping(method = RequestMethod.GET, value = "/recommendations")
    List<RecommendationsResponse> getPersonalizedRecommendations(@RequestParam UUID storeId, @RequestParam UUID userId);

}
