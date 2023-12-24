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
    public List<ProductData> searchByCategory(@PathVariable UUID categoryExternalId, @RequestParam int page) {
        return searchFacade.searchByCategory(categoryExternalId).stream()
                .skip((page - 1) * 50)
                .limit(page * 50)
                .toList();
    }

    @GetMapping
    public List<ProductData> searchByCategory(@RequestParam String term, @RequestParam int page) {
        return searchFacade.searchByTerm(term).stream()
                .skip((page - 1) * 50)
                .limit(page * 50)
                .toList();
    }


}
