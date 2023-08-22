package com.eatit.eatitapiv3.api.restaurant.dto;

import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;

import java.util.Set;

public record RestaurantSummaryResponse(
        Long id,
        String name,
        String subName,
        Set<RestaurantTimeTable> restaurantTimeTables,
        Boolean isOpened,
        Boolean mealTicketEnabled
) {
    public static RestaurantSummaryResponse toEntity(Restaurant restaurant, Set<RestaurantTimeTable> timeTables){
        return new RestaurantSummaryResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getSubName(),
                timeTables,
                restaurant.isOpened(),
                restaurant.isMealTicketEnabled()
        );
    }
}
