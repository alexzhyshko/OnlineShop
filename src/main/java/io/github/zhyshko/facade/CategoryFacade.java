package io.github.zhyshko.facade;

import io.github.zhyshko.dto.product.CategoryData;

import java.util.List;

public interface CategoryFacade {
    List<CategoryData> getAll();

}
