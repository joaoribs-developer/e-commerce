package com.jgl.ecommerce.domain.model;

import com.jgl.ecommerce.application.payloads.ItemForm;
import com.jgl.ecommerce.domain.model.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_sequence", allocationSize = 1)
    private Long id;

    private String description;
    private Integer quantity;
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    protected Item() {}

    public Item(Long id, String description, Integer quantity, BigDecimal value, Order order) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
        this.order = order;
    }

    public static List<Item> from(List<ItemForm> forms, Order order) {
        return forms.stream()
                .map(form -> new Item(null, form.description(), form.quantity(), form.value(), order))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Order getOrder() {
        return order;
    }
}
