package com.eatit.eatitapiv3.domain.restaurant;

import com.eatit.eatitapiv3.api.restaurant.dto.TimeTableCreateRequest;
import com.eatit.eatitapiv3.api.restaurant.dto.TimeTableUpdateRequest;
import com.eatit.eatitapiv3.exception.DuplicateException;
import com.eatit.eatitapiv3.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RestaurantTimeTableService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantTimeTableRepository restaurantTimeTableRepository;

    @Transactional
    public void createTimeTable(Long restaurantId, Set<TimeTableCreateRequest> timeTables) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id " + restaurantId));

        List<RestaurantTimeTable> timeTableList = new ArrayList<>();

        for (TimeTableCreateRequest timeTable : timeTables) {
            checkDuplicateDayType(restaurantId, timeTable.dayType());
            RestaurantTimeTable roh = RestaurantTimeTable.builder()
                    .restaurant(restaurant)
                    .dayType(timeTable.dayType())
                    .isClosed(timeTable.isClosed())
                    .endTime(timeTable.endTime())
                    .startTime(timeTable.startTime())
                    .breakTimes(timeTable.breakTimes())
                    .essentialBuild();
            timeTableList.add(roh);
        }

        restaurantTimeTableRepository.saveAll(timeTableList);
    }

    private void checkDuplicateDayType(Long restaurantId, String dayType) {
        restaurantTimeTableRepository.findByRestaurantIdAndDayType(restaurantId, dayType).ifPresent(data -> {
            throw new DuplicateException("Duplicate entry : " + dayType + " already exists.");
        });

    }

    @Transactional(readOnly = true)
    public Set<RestaurantTimeTable> getTimeTables(Long restaurantId) {
        return restaurantTimeTableRepository.findAllByRestaurantId(restaurantId)
                .orElseThrow(() -> new NotFoundException("TimeTable not found with restaurant Id" + restaurantId));

    }

    @Transactional
    public void updateTimeTables(Long id,  Set<TimeTableUpdateRequest>  timeTableUpdateRequest) {
        for (TimeTableUpdateRequest updateTimeTableRequest : timeTableUpdateRequest) {
            Optional<RestaurantTimeTable> restaurantTimeTable = restaurantTimeTableRepository.findByRestaurantIdAndDayType(id, updateTimeTableRequest.dayType());
            restaurantTimeTable.ifPresent(data -> {
                data.update(TimeTableUpdateRequest.toTimeTable(updateTimeTableRequest));
            });
        }

    }
}
