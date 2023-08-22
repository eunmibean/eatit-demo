package com.eatit.eatitapiv3.config;

import org.junit.jupiter.api.Tag;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag("docker")
@Testcontainers
public abstract class MariaDBTestContainer {

    @Container
    static final MariaDBContainer<?> MARIA_DB_CONTAINER;

    static {
        MARIA_DB_CONTAINER = new MariaDBContainer<>("mariadb:10.5");
        MARIA_DB_CONTAINER.withDatabaseName("eat_it")
                          .withUsername("test")
                          .withPassword("test")
                          .withReuse(true)
                          .start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MARIA_DB_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MARIA_DB_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MARIA_DB_CONTAINER::getPassword);
    }
}
