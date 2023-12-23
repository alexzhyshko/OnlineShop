package io.github.zhyshko.mapper.dto.product;

import io.github.zhyshko.dto.product.*;
import io.github.zhyshko.model.product.*;
import io.github.zhyshko.service.product.ProductService;
import io.github.zhyshko.service.product.ProductTypeService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ProductAttributeMapper.class, AuthorMapper.class, PublisherMapper.class, CategoryMapper.class})
public abstract class ProductMapper {

    @Autowired
    protected ProductAttributeMapper productAttributeMapper;
    @Autowired
    protected AuthorMapper authorMapper;
    @Autowired
    protected PublisherMapper publisherMapper;
    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected ProductService productService;
    @Autowired
    protected ProductTypeService productTypeService;
    
    public Product toModel(ProductData orderData) {
        if ( orderData == null ) {
            return null;
        }
        Optional<Product> product = productService.get(orderData.getExternalId());

        return product.orElseGet(() -> productService.getOrCreate(createProduct(orderData)));
    }

    @Mapping(target = "type", expression = "java(product.getType().getName())")
    public abstract ProductData toDto(Product product);

    public List<ProductData> toDtoList(List<Product> products) {
        return products.stream().map(this::toDto).toList();
    }

    private Product createProduct(ProductData productData) {
        Product.ProductBuilder<?, ?> product = Product.builder();

        product.externalId( productData.getExternalId() );
        product.productAttributes( productAttributeDataListToProductAttributeList( productData.getProductAttributes() ) );
        product.authors( authorDataListToAuthorList( productData.getAuthors() ) );
        product.publishers( publisherDataListToPublisherList( productData.getPublishers() ) );
        product.categories( categoryDataListToCategoryList( productData.getCategories() ) );
        product.name(productData.getName());
        product.price(productData.getPrice());
        product.image(productData.getImage());
        product.type(productTypeService.getOrCreate(productData.getType()));

        return product.build();
    }

    protected List<ProductAttribute> productAttributeDataListToProductAttributeList(List<ProductAttributeData> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductAttribute> list1 = new ArrayList<ProductAttribute>( list.size() );
        for ( ProductAttributeData productAttributeData : list ) {
            list1.add( productAttributeMapper.toModel( productAttributeData ) );
        }

        return list1;
    }

    protected List<Author> authorDataListToAuthorList(List<AuthorData> list) {
        if ( list == null ) {
            return null;
        }

        List<Author> list1 = new ArrayList<Author>( list.size() );
        for ( AuthorData authorData : list ) {
            list1.add( authorMapper.toModel( authorData ) );
        }

        return list1;
    }

    protected List<Publisher> publisherDataListToPublisherList(List<PublisherData> list) {
        if ( list == null ) {
            return null;
        }

        List<Publisher> list1 = new ArrayList<Publisher>( list.size() );
        for ( PublisherData publisherData : list ) {
            list1.add( publisherMapper.toModel( publisherData ) );
        }

        return list1;
    }

    protected List<Category> categoryDataListToCategoryList(List<CategoryData> list) {
        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        for ( CategoryData categoryData : list ) {
            list1.add( categoryMapper.toModel( categoryData ) );
        }

        return list1;
    }

}
