package com.eatit.eatitapiv3.domain.restaurant;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "restaurant_time_table")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class RestaurantTimeTable {

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_type", nullable = false, length = 256)
    private String dayType;

    @Column(name = "is_closed", nullable = true)
    private Boolean isClosed;

    @Column(name = "start_time", nullable = true, length = 5)
    private String startTime;

    @Column(name = "end_time", nullable = true, length = 5)
    private String endTime;

    @Column(name = "break_times", nullable = true)
    private String breakTimes;

    @Builder(buildMethodName = "essentialBuild")
    public RestaurantTimeTable(Restaurant restaurant, String dayType, Boolean isClosed, String startTime, String endTime, String breakTimes) {
        this.restaurant = restaurant;
        this.dayType = dayType;
        this.isClosed = isClosed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakTimes = breakTimes;

    }

    public void update(RestaurantTimeTable timeTableRequest) {
        this.dayType = timeTableRequest.getDayType();
        this.isClosed = timeTableRequest.getIsClosed();
        this.startTime = timeTableRequest.getStartTime();
        this.endTime = timeTableRequest.getEndTime();
        this.breakTimes = timeTableRequest.getBreakTimes();
    }
}
