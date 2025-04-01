package com.jgl.ecommerce.domain;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(UUID id) {
        super("Can't find any order with ID [%s]".formatted(id));
    }
}
