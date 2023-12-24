package io.github.zhyshko.facade;

import io.github.zhyshko.dto.product.ProductData;

import java.util.List;
import java.util.UUID;

public interface SearchFacade {

    List<ProductData> searchByCategory(UUID externalId);
    List<ProductData> searchByTerm(String term);

}
