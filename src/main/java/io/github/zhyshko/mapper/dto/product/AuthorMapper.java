package io.github.zhyshko.mapper.dto.product;

import io.github.zhyshko.dao.product.AuthorDao;
import io.github.zhyshko.dto.product.AuthorData;
import io.github.zhyshko.model.product.Author;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {

    @Autowired
    protected AuthorDao authorDao;

    public Author toModel(AuthorData orderData) {
        if ( orderData == null ) {
            return null;
        }

        return authorDao.findByExternalId(orderData.getExternalId())
                .orElseGet(() -> authorDao.save(createAuthor(orderData)));
    }
    public abstract AuthorData toDto(Author order);

    public List<AuthorData> toDtolist(List<Author> authorList) {
        return authorList
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Author createAuthor(AuthorData authorData) {
        Author.AuthorBuilder<?, ?> author = Author.builder();

        author.externalId( authorData.getExternalId() );
        author.email(authorData.getEmail());
        author.firstName(authorData.getFirstName());
        author.lastName(authorData.getLastName());
        author.phone(authorData.getPhone());

        return author.build();
    }

}
