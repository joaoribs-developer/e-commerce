package com.jgl.ecommerce.infrastructure.migrations;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DBConstructor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        String createTablesSQL =
                """
                        CREATE TABLE IF NOT EXISTS "order" (
                            id UUID PRIMARY KEY,
                            number UUID NOT NULL,
                            requester VARCHAR(255) NOT NULL,
                            total NUMERIC(18, 2) NOT NULL
                        );
                        
                        CREATE SEQUENCE IF NOT EXISTS item_sequence START WITH 1 INCREMENT BY 1;
                        
                        CREATE TABLE IF NOT EXISTS "item" (
                            id BIGINT PRIMARY KEY DEFAULT nextval('item_sequence'),
                            description VARCHAR(255) NOT NULL,
                            quantity INTEGER NOT NULL,
                            value DECIMAL(19, 4) NOT NULL,
                            order_id UUID NOT NULL,
                            CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE
                        );
                        """;



        jdbcTemplate.execute(createTablesSQL);
    }
}
