package com.eatit.eatitapiv3.api.restaurant.dto;

import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;

import java.sql.Time;

public record TimeTableUpdateRequest(
        String dayType,
        Boolean isClosed,
        String startTime,
        String endTime,
        String breakTimes
) {
    public static RestaurantTimeTable toTimeTable(TimeTableUpdateRequest timeTableUpdateRequest){
        return RestaurantTimeTable.builder()
                .dayType(timeTableUpdateRequest.dayType())
                .isClosed(timeTableUpdateRequest.isClosed)
                .startTime(timeTableUpdateRequest.startTime)
                .endTime(timeTableUpdateRequest.endTime)
                .breakTimes(timeTableUpdateRequest.breakTimes)
                .essentialBuild();
    }
}
