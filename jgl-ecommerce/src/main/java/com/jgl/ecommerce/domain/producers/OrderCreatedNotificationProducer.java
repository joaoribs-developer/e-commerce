package com.jgl.ecommerce.domain.producers;

import com.jgl.ecommerce.domain.services.CreateOrderService;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreatedNotificationProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedNotificationProducer.class);
    private final String topic;

    private final SnsTemplate snsTemplate;

    public OrderCreatedNotificationProducer(@Value("${sns.order.notification-topic}") String topic, SnsTemplate snsTemplate) {
        this.topic = topic;
        this.snsTemplate = snsTemplate;
    }

    @Async
    @EventListener
    public void listen(CreateOrderService.OrderCreatedEvent event) {

        final var order = event.order();
        log.debug("Trying to send notification about order [{}]", order.getNumber());
        snsTemplate.sendNotification(topic, SnsNotification.of(order));
        log.info("Notification about order [{}] sent", order.getNumber());
    }
}
