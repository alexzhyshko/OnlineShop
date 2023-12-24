package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.product.CategoryData;
import io.github.zhyshko.facade.CategoryFacade;
import io.github.zhyshko.mapper.dto.product.CategoryMapper;
import io.github.zhyshko.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCategoryFacade implements CategoryFacade {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryData> getAll() {
        return categoryMapper.toDtolist(categoryService.getAll());
    }
}
