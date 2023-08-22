package com.eatit.eatitapiv3.api.restaurant.dto;

import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;

public record TimeTableSummaryResponse(
        String dayType,
        Boolean isClosed,
        String startTime,
        String endTime,
        String breakTimes
) {
    public static TimeTableSummaryResponse toResponse(RestaurantTimeTable restaurantTimeTable)
    {
        return new TimeTableSummaryResponse(
                restaurantTimeTable.getDayType(),
                restaurantTimeTable.getIsClosed(),
                restaurantTimeTable.getStartTime(),
                restaurantTimeTable.getEndTime(),
                restaurantTimeTable.getBreakTimes()
        );
    }}


