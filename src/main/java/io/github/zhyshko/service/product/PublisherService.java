package io.github.zhyshko.service.product;

import io.github.zhyshko.model.product.Publisher;

import java.util.List;
import java.util.UUID;

public interface PublisherService {

    Publisher getOrCreate(Publisher publisher);
}
