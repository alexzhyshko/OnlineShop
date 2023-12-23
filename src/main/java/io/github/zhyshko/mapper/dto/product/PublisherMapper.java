package io.github.zhyshko.mapper.dto.product;

import io.github.zhyshko.dao.product.PublisherDao;
import io.github.zhyshko.dto.product.PublisherData;
import io.github.zhyshko.mapper.dto.user.AddressMapper;
import io.github.zhyshko.model.product.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PublisherMapper {

    @Autowired
    protected PublisherDao publisherDao;
    protected AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    public Publisher toModel(PublisherData orderData) {
        if ( orderData == null ) {
            return null;
        }

        return publisherDao.findByExternalId(orderData.getExternalId())
                .orElseGet(() -> publisherDao.save(createPublisher(orderData)));
    }
    public abstract PublisherData toDto(Publisher order);

    public List<PublisherData> toDtolist(List<Publisher> publisherList) {
        return publisherList
                .stream()
                .map(this::toDto)
                .toList();
    }

    private Publisher createPublisher(PublisherData publisherData) {
        Publisher.PublisherBuilder<?, ?> publisher = Publisher.builder();

        publisher.externalId( publisherData.getExternalId() );
        publisher.address(addressMapper.toModel(publisherData.getAddress()));
        publisher.email(publisherData.getEmail());
        publisher.website(publisherData.getWebsite());
        publisher.name(publisherData.getName());
        publisher.phone(publisherData.getPhone());

        return publisher.build();
    }

}
