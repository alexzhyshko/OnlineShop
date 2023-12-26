package io.github.zhyshko.mapper.dto.product;

import io.github.zhyshko.dao.product.ProductAttributeDao;
import io.github.zhyshko.dto.product.ProductAttributeData;
import io.github.zhyshko.model.product.ProductAttribute;
import io.github.zhyshko.service.product.ProductAttributeService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductAttributeMapper {

    @Autowired
    protected ProductAttributeService productAttributeService;

    public ProductAttribute toModel(ProductAttributeData productAttributeData) {
        if ( productAttributeData == null ) {
            return null;
        }

        return productAttributeService.getOrCreate(createProductAttribute(productAttributeData));
    }
    public abstract ProductAttributeData toDto(ProductAttribute order);

    public List<ProductAttributeData> toDtolist(List<ProductAttribute> attributeList) {
        return attributeList
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ProductAttribute createProductAttribute(ProductAttributeData productAttributeData) {
        ProductAttribute.ProductAttributeBuilder<?, ?> productAttribute = ProductAttribute.builder();

        productAttribute.externalId( productAttributeData.getExternalId() );
        productAttribute.value(productAttributeData.getValue());
        productAttribute.name(productAttributeData.getName());

        return productAttribute.build();
    }
}
