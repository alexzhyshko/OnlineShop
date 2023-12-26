package io.github.zhyshko.service.product;

import io.github.zhyshko.model.product.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    Author getOrCreate(Author author);
}
