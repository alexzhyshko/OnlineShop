package io.github.zhyshko.service.product.impl;

import io.github.zhyshko.dao.product.PublisherDao;
import io.github.zhyshko.model.product.Publisher;
import io.github.zhyshko.service.product.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultPublisherService implements PublisherService {

    @Autowired
    private PublisherDao publisherDao;

    @Override
    public Publisher getOrCreate(Publisher publisher) {
        return publisherDao.findByName(publisher.getName())
                .orElseGet(() -> publisherDao.save(publisher));
    }
}
