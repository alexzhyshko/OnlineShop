package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.product.CategoryData;
import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.RecommendationsFacade;
import io.github.zhyshko.facade.SearchFacade;
import io.github.zhyshko.mapper.dto.product.CategoryMapper;
import io.github.zhyshko.mapper.dto.product.ProductMapper;
import io.github.zhyshko.model.product.Product;
import io.github.zhyshko.service.product.CategoryService;
import io.github.zhyshko.service.product.ProductService;
import io.github.zhyshko.wsdto.product.ProductWsDto;
import io.github.zhyshko.wsdto.recommmendations.RecommendationsRecord;
import io.github.zhyshko.wsdto.recommmendations.RecommendationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultSearchFacade implements SearchFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RecommendationsFacade recommendationsFacade;

    @Override
    public List<ProductData> searchByCategory(UUID externalId) {
        List<UUID> categoryExternalIds = flatten(categoryMapper.toDto(categoryService.getByExternalId(externalId))).stream()
                .distinct()
                .toList();

        List<ProductData> productSearchResult = mapList(productService.getAllByCategories(categoryExternalIds)) ;

        return alignWithRecommendations(productSearchResult);
    }

    @Override
    public List<ProductData> searchByTerm(String term) {
        List<ProductData> productSearchResult = mapList(productService.getAllByTerm(term));

        return alignWithRecommendations(productSearchResult);
    }

    private List<ProductData> mapList(List<Product> products) {
        return productMapper.toDtoList(products);
    }

    private List<ProductData> alignWithRecommendations(List<ProductData> productSearchResult) {
        Map<UUID, RecommendationsResponse> recommendationsResult = recommendationsFacade.getRecommendations().stream()
                .collect(Collectors.toMap(RecommendationsResponse::externalId, Function.identity()));

        return productSearchResult.stream()
                .map(p -> new RecommendationsRecord(p, getScore(recommendationsResult.get(p.getExternalId()))))
                .sorted(Comparator.comparingLong(RecommendationsRecord::score).reversed())
                .map(RecommendationsRecord::productData)
                .toList();
    }

    private Long getScore(RecommendationsResponse response) {
        return response == null?0L:response.score();
    }

    private List<UUID> flatten(CategoryData categoryData) {
        return Stream.concat(
                        Stream.of(categoryData.getExternalId()),
                        categoryData.getSubcategories().stream()
                                .flatMap(categoryData1 -> flatten(categoryData1).stream())
                )
                .toList();
    }
}
