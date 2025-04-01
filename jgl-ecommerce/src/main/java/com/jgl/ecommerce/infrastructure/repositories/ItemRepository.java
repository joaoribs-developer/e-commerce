package com.jgl.ecommerce.infrastructure.repositories;

import com.jgl.ecommerce.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
