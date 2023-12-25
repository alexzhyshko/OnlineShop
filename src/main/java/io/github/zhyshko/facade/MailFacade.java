package io.github.zhyshko.facade;

import io.github.zhyshko.dto.order.OrderData;

public interface MailFacade {
    void sendReviewEmail(OrderData orderData);
}
