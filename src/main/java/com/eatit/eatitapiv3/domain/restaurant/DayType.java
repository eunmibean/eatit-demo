package com.eatit.eatitapiv3.domain.restaurant;

public enum DayType {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday"),
    HOLIDAY("Holiday");

    private final String dayType;

    DayType(String dayType) {
        this.dayType = dayType;
    }
}
