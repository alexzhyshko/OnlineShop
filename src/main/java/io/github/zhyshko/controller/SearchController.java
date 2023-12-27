package io.github.zhyshko.controller;

import io.github.zhyshko.dto.product.ProductData;
import io.github.zhyshko.facade.SearchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchFacade searchFacade;

    @GetMapping("/{categoryExternalId}")
    public List<ProductData> searchByCategory(@PathVariable UUID categoryExternalId) {
        return searchFacade.searchByCategory(categoryExternalId);
    }

    @GetMapping
    public List<ProductData> searchByTerm(@RequestParam String term) {
        return searchFacade.searchByTerm(term);
    }


}
