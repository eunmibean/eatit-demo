package com.eatit.eatitapiv3.api.restaurant.dto;

import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
public record UpdateRestaurantRequest(
        @NotNull
        String name,
        String subName,
        String summary,
        String description,
        String location,
        String phoneNumber,
        Integer tableCounts,
        Set<String> categories,
        boolean isOpened
) {
        public Restaurant toRestaurant(){
                return Restaurant.builder()
                        .name(this.name)
                        .subName(this.subName)
                        .summary(this.summary)
                        .description(this.description)
                        .location(this.location)
                        .phoneNumber(this.phoneNumber)
                        .tablesCount(this.tableCounts)
                        .isOpened(this.isOpened)
                        .essentialBuild();

        }
}
