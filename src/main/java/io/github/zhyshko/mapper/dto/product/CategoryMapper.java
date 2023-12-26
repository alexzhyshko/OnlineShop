package io.github.zhyshko.mapper.dto.product;

import io.github.zhyshko.dao.product.CategoryDao;
import io.github.zhyshko.dto.product.CategoryData;
import io.github.zhyshko.model.product.Category;
import io.github.zhyshko.service.product.CategoryService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Autowired
    protected CategoryService categoryService;

    public Category toModel(CategoryData categoryData) {
        if ( categoryData == null ) {
            return null;
        }

        return categoryService.findOrCreate(createCategory(categoryData));
    }
    public abstract CategoryData toDto(Category order);

    public List<CategoryData> toDtolist(List<Category> categoryList) {
        return categoryList
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Category createCategory(CategoryData categoryData) {
        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.externalId( categoryData.getExternalId() );
        category.subcategories( categoryDataListToCategoryList( categoryData.getSubcategories() ) );
        category.name(categoryData.getName());

        return category.build();
    }

    protected List<Category> categoryDataListToCategoryList(List<CategoryData> list) {
        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        for ( CategoryData categoryData : list ) {
            list1.add( toModel( categoryData ) );
        }

        return list1;
    }
}
