package io.github.zhyshko.facade.impl;

import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.facade.MailFacade;
import org.springframework.stereotype.Component;

@Component
public class DefaultMailFacade implements MailFacade {


    @Override
    public void sendReviewEmail(OrderData orderData) {

    }
}
