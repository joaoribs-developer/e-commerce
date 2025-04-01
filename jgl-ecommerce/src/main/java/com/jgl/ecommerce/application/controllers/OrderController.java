package com.jgl.ecommerce.application.controllers;

import com.jgl.ecommerce.application.payloads.OrderForm;
import com.jgl.ecommerce.domain.OrderNotFoundException;
import com.jgl.ecommerce.domain.model.Order;
import com.jgl.ecommerce.domain.services.CreateOrderService;
import com.jgl.ecommerce.infrastructure.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CreateOrderService createOrderService;

    public OrderController(OrderRepository orderRepository, CreateOrderService createOrderService) {
        this.orderRepository = orderRepository;
        this.createOrderService = createOrderService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OrderForm form) {

        final var order = Order.from(form);

        createOrderService.save(order);

        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable UUID id) {

        final var order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return ResponseEntity.ok(order);
    }
}
