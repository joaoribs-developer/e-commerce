package com.jgl.ecommerce.application.payloads;

import java.math.BigDecimal;

public record ItemForm(
        String description,
        Integer quantity,
        BigDecimal value
) {
    public BigDecimal calculateItemTotal() {
        return value().multiply(new BigDecimal(quantity()));
    }
}
