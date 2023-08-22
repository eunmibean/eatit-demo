package com.eatit.eatitapiv3.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RestaurantTimeTableRepository extends JpaRepository<RestaurantTimeTable, Long> {
    Optional<RestaurantTimeTable> findByRestaurantIdAndDayType(Long restaurantId, String dayType);
    Optional<Set<RestaurantTimeTable>> findAllByRestaurantId(Long restauraintId);

}
