package com.eatit.eatitapiv3.domain.restaurant;

import com.eatit.eatitapiv3.config.JpaTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RestaurantRepositoryTest extends JpaTestHelper {

    @Autowired
    RestaurantRepository repository;

    @Test
    @Sql("classpath:db/restaurant.sql")
    void find(){
        final var restaurant = repository.findById(1L);
        assertThat(restaurant.get().getId()).isEqualTo(1L);
    }
}
