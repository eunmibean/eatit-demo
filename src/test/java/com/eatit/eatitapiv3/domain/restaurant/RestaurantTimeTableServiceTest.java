package com.eatit.eatitapiv3.domain.restaurant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.eatit.eatitapiv3.mock.RestaurantMockCreator.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantTimeTableServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantTimeTableRepository restaurantTimeTableRepository;

    @InjectMocks
    private RestaurantTimeTableService restaurantTimeTableService;

    @Test
    void createTimeTable() {
        final var timeTables = createTimeTableRequest();
        final var restaurant = createRestaurant();


        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(restaurantTimeTableRepository.findByRestaurantIdAndDayType(1L, "FRIDAY")).thenReturn(Optional.empty());

        restaurantTimeTableService.createTimeTable(1L, timeTables);

        verify(restaurantTimeTableRepository, times(8)).save(any(RestaurantTimeTable.class));
    }

    @Test
    void getTimeTables() {
        final var timeTables = createTimeTables();


        when(restaurantTimeTableRepository.findAllByRestaurantId(1L)).thenReturn(Optional.of(timeTables));
        restaurantTimeTableService.getTimeTables(1L);
        verify(restaurantTimeTableRepository, times(1)).findAllByRestaurantId(anyLong());
    }

    @Test
    void updateTimeTables() {
        final var updateTimeTable = createTimeTableUpdateRequestSet();
        final var timeTable = createOneTimeTable();
        final var restaurantId = 1L;
        when(restaurantTimeTableRepository.findByRestaurantIdAndDayType(restaurantId, DayType.MONDAY.name()))
                .thenReturn(Optional.of(timeTable));

        restaurantTimeTableService.updateTimeTables(restaurantId, updateTimeTable);

        verify(restaurantTimeTableRepository, times(updateTimeTable.size()))
                .findByRestaurantIdAndDayType(eq(restaurantId), anyString());



    }
}
