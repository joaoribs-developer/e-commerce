package com.jgl.ecommerce.domain.model;

import com.jgl.ecommerce.application.payloads.ItemForm;
import com.jgl.ecommerce.application.payloads.OrderForm;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "order")
public class Order {

    @Id
    private UUID id;
    private UUID number;
    private String requester;
    private BigDecimal total;
    @Transient
    private List<Item> items;

    protected Order() {}

    public Order(UUID id, UUID number, String requester, BigDecimal total, List<Item> items) {
        this.id = id;
        this.number = number;
        this.requester = requester;
        this.total = total;
        this.items = items;
    }

    public Order copy(
            UUID id,
            UUID number,
            String requester,
            BigDecimal total,
            List<Item> items
    ) {
        return new Order(
                id != null ? id : this.id,
                number != null ? number : this.number,
                requester != null ? requester : this.requester,
                total != null ? total : this.total,
                items != null ? items : this.items
        );
    }

    public static Order from(OrderForm form) {

        final var total = form.items()
                .stream()
                .map(ItemForm::calculateItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var order = new Order(UUID.randomUUID(), form.number(), form.requester(), total, null);

        final var orderItems = Item.from(form.items(), order);

        return order.copy(null, null, null, null, orderItems);
    }

    public UUID getId() {
        return id;
    }

    public UUID getNumber() {
        return number;
    }

    public String getRequester() {
        return requester;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Item> getItems(){return items;}
}


