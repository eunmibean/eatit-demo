package com.eatit.eatitapiv3.application.restaurant;

import com.eatit.eatitapiv3.api.restaurant.dto.RestaurantSummaryResponse;
import com.eatit.eatitapiv3.api.restaurant.dto.TimeTableSummaryResponse;
import com.eatit.eatitapiv3.domain.restaurant.*;
import com.eatit.eatitapiv3.domain.user.BaseUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.eatit.eatitapiv3.mock.RestaurantMockCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerRestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantTimeTableService restaurantTimeTableService;

    @InjectMocks
    private OwnerRestaurantService ownerRestaurantService;


    @Test
    void update() {
        final var restaurant = createRestaurant();
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Restaurant updatedRestaurant = ownerRestaurantService.update(restaurant, 1L);

        assertEquals("Gelato가 조아요", updatedRestaurant.getName());
        assertEquals("Roma", updatedRestaurant.getLocation());
    }

    @Test
    void updateStatus() {
        final var restaurant = createRestaurant();
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Restaurant updatedRestaurant = ownerRestaurantService.updateStatus(1L, false);

        assertNotNull(updatedRestaurant);
        assertFalse(updatedRestaurant.isOpened());

    }

    @Test
    void getTimeTables() {
        final var timeTables = createTimeTables();
        when(restaurantTimeTableService.getTimeTables(1L)).thenReturn(timeTables);
        Set<TimeTableSummaryResponse> timeTableSet = ownerRestaurantService.getTimeTables(1L);

        assertNotNull(timeTableSet);
    }


    @Test
    void readAllMyRestaurant() {
        final var user = new BaseUser();
        final var restaurants = Arrays.asList(createRestaurant());
        final var timetables = createTimeTables();
        when(restaurantRepository.findAllByOwnerId(user.getId())).thenReturn(restaurants);
        when(restaurantTimeTableService.getTimeTables(1L)).thenReturn(timetables);

        List<RestaurantSummaryResponse> result = ownerRestaurantService.readAllMyRestaurant(user);
        assertNotNull(result);
    }

    @Test
    void updateMealTicketEnabled() {
        final var id = 1L;
        final var enabled = false;
        final var restaurant = createRestaurant();
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        ownerRestaurantService.updateMealTicketEnabled(id, enabled);
        assertFalse(restaurant.isMealTicketEnabled());

    }
}

