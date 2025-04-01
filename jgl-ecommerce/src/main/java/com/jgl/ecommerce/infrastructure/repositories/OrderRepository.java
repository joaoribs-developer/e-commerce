package com.jgl.ecommerce.infrastructure.repositories;

import com.jgl.ecommerce.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
