package com.jgl.ecommerce.domain.services;

import com.jgl.ecommerce.domain.model.Order;
import com.jgl.ecommerce.infrastructure.repositories.ItemRepository;
import com.jgl.ecommerce.infrastructure.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateOrderService {

    private static final Logger log = LoggerFactory.getLogger(CreateOrderService.class);
    private final ApplicationEventPublisher eventPublisher;

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    public CreateOrderService(ApplicationEventPublisher eventPublisher, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.eventPublisher = eventPublisher;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public void save(Order order) {
        log.debug("Trying to register order [{}]", order.getNumber());

        orderRepository.save(order);
        log.info("Saved order [{}]", order.getNumber());

        log.debug("Trying to register items by order [{}]", order.getNumber());
        itemRepository.saveAll(order.getItems());
        log.info("Saved items by order [{}]", order.getNumber());

        eventPublisher.publishEvent(new OrderCreatedEvent(order));
        log.info("Internal event published for order [{}]", order.getNumber());
    }

    public record OrderCreatedEvent(Order order) {
    }
}
