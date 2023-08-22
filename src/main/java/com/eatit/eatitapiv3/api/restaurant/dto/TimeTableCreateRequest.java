package com.eatit.eatitapiv3.api.restaurant.dto;

import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record TimeTableCreateRequest(

        @NotNull
        Long restaurantId,
        @NotNull
        String dayType,
        @NotNull
        boolean isClosed,
        @NotNull
        String startTime,
        @NotNull
        String endTime,
        String breakTimes

) {
    public RestaurantTimeTable toEntity(Restaurant restaurant) {
        return RestaurantTimeTable.builder()
                .restaurant(restaurant)
                .dayType(this.dayType)
                .isClosed(this.isClosed)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .breakTimes(this.breakTimes)
                .essentialBuild();

    }
}
