package com.eatit.eatitapiv3.mock;

import com.eatit.eatitapiv3.api.restaurant.dto.*;
import com.eatit.eatitapiv3.domain.restaurant.DayType;
import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;

import java.util.HashSet;
import java.util.Set;

public class RestaurantMockCreator {


    public static Restaurant createRestaurant() {
        return Restaurant.builder()

                .name("Churros Restaurant")
                .subName("서울본점")
                .summary("스페인식 츄로스")
                .description("스페인식 츄로스")
                .phoneNumber("02 3232 3232")
                .location("서울")
                .isOpened(true)
                .mealTicketEnabled(false)
                .essentialBuild();


    }


    public static Set<TimeTableCreateRequest> createTimeTableRequest() {
        Set<TimeTableCreateRequest> timeTables = new HashSet<>();
        for (DayType dayType : DayType.values()) {
            timeTables.add(new TimeTableCreateRequest(
                    1L,
                    dayType.name(),
                    false,
                    "09:00:00",
                    "22:00:00",
                    null
            ));
        }
        return timeTables;
    }

    public static Set<RestaurantTimeTable> createTimeTables() {
        Set<RestaurantTimeTable> timeTables = new HashSet<>();
        for (DayType dayType : DayType.values()) {
            RestaurantTimeTable timetable = RestaurantTimeTable.builder()
                    .restaurant(createRestaurant())
                    .dayType(dayType.name())
                    .isClosed(false)
                    .startTime("09:00:00")
                    .endTime("21:00:00")
                    .breakTimes(null)
                    .essentialBuild();

            timeTables.add(timetable);
        }
        return timeTables;
    }

    public static Set<TimeTableSummaryResponse> createTimeTableSummaryResponse() {
        Set<TimeTableSummaryResponse> tables = new HashSet<>();
        for (var timeTable : createTimeTables()) {
            tables.add(TimeTableSummaryResponse.toResponse(timeTable));
        }
        return tables;
    }

    public static UpdateRestaurantRequest createUpdateRequest() {
        return new UpdateRestaurantRequest(
                "Gelato가 조아요",
                "서울본점",
                "이탈리안 젤라또",
                "이탈리안 젤라또",
                "Roma",
                "02 1234 1234",
                0,
                Set.of("desert"),
                true
        );
    }

    public static Set<TimeTableUpdateRequest> createTimeTableUpdateRequestSet() {
        Set<TimeTableUpdateRequest> timeTables = new HashSet<>();
        timeTables.add(new TimeTableUpdateRequest(
                DayType.MONDAY.name(),
                true,
                null,
                null,
                null
        ));

        return timeTables;
    }

    public static TimeTableUpdateRequest createTimeTableUpdateRequest() {
        return new TimeTableUpdateRequest(
                DayType.MONDAY.name(),
                true,
                null,
                null,
                null
        );
    }

    public static RestaurantTimeTable createOneTimeTable() {
        return RestaurantTimeTable.builder()
                .restaurant(createRestaurant())
                .isClosed(false)
                .startTime("09:00:00")
                .endTime("21:00:00")
                .breakTimes(null)
                .essentialBuild();
    }

    public static RestaurantSummaryResponse createRestaurantSummaryResponse(){
        return RestaurantSummaryResponse.toEntity(createRestaurant(), createTimeTables());
    }
}
