package io.github.zhyshko.controller;

import io.github.zhyshko.dto.product.CategoryData;
import io.github.zhyshko.facade.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping
    public List<CategoryData> getAll(){
        return categoryFacade.getAll();
    }

}
