package com.jgl.ecommerce.application.payloads;

import java.util.List;
import java.util.UUID;

public record OrderForm(
        UUID number,
        String requester,
        List<ItemForm> items
) {
}
