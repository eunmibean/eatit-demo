package com.eatit.eatitapiv3.application.restaurant;

import com.eatit.eatitapiv3.api.restaurant.dto.*;
import com.eatit.eatitapiv3.domain.restaurant.*;
import com.eatit.eatitapiv3.domain.user.BaseUser;
import com.eatit.eatitapiv3.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OwnerRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantTimeTableService restaurantTimeTableService;


    @Transactional
    public Restaurant update(Restaurant updateRestaurant, Long restaurantId) {
        var restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id " + restaurantId));

        restaurant.update(updateRestaurant);
        return restaurant;

    }

    @Transactional
    public Restaurant updateStatus(Long id, Boolean status) {
        var restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id " + id));
        restaurant.updateStatus(status);
        return restaurant;

    }

    public Set<TimeTableSummaryResponse> getTimeTables(Long id) {
        final var restaurantTimeTableSet =  restaurantTimeTableService.getTimeTables(id);
        var resultSet = new HashSet<>();
        for (RestaurantTimeTable timeTable : restaurantTimeTableSet){
            resultSet.add(TimeTableSummaryResponse.toResponse(timeTable));
        }
        return resultSet;

    }

    public void updateTimeTables(Long id, Set<TimeTableUpdateRequest> timeTableUpdateRequest){
        restaurantTimeTableService.updateTimeTables(id, timeTableUpdateRequest);
    }


    public List<RestaurantSummaryResponse> readAllMyRestaurant(BaseUser user){
        final var ownerId = user.getId();
        final var restaurants = restaurantRepository.findAllByOwnerId(ownerId);
        var summary = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            Set<RestaurantTimeTable> timeTables = restaurantTimeTableService.getTimeTables(restaurant.getId());
            summary.add(RestaurantSummaryResponse.toEntity(restaurant, timeTables));
        }
        return summary;

    }

    @Transactional
    public void updateMealTicketEnabled(Long id, Boolean mealTicketEnabled){
        var restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with id " + id));

        restaurant.updateMealTicketEnabled(mealTicketEnabled);
    }
}
