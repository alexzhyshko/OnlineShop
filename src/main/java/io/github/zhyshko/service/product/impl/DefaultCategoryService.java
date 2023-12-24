package io.github.zhyshko.service.product.impl;

import io.github.zhyshko.dao.product.CategoryDao;
import io.github.zhyshko.model.product.Category;
import io.github.zhyshko.service.product.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAllBySupercategoryIdIsNull();
    }

    @Override
    public Category getByExternalId(UUID externalId) {
        return categoryDao.findByExternalId(externalId).orElseThrow(() -> new RuntimeException("No such category found"));
    }
}
