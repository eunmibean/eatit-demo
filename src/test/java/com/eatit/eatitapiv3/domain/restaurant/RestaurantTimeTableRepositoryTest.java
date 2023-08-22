package com.eatit.eatitapiv3.domain.restaurant;

import com.eatit.eatitapiv3.config.JpaTestHelper;
import com.eatit.eatitapiv3.mock.RestaurantMockCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTimeTableRepositoryTest  extends JpaTestHelper {

    @Autowired
    private RestaurantTimeTableRepository restaurantTimeTableRepository;

    @Test
    void save() {
        final var timetable =
                RestaurantTimeTable.builder()
                        .restaurant(RestaurantMockCreator.createRestaurant())
                        .dayType("MON")
                        .startTime("09:00")
                        .endTime("21:00")
                        .isClosed(false)
                        .breakTimes("[\"14:00-15:30\", \"20:00-21:30\"]")
                        .essentialBuild();
        restaurantTimeTableRepository.save(timetable);
        final var findTimeTable = restaurantTimeTableRepository.findById(timetable.getId()).get();

        assertThat(timetable).isEqualTo(findTimeTable);
    }

    @Test
    @Sql("classpath:db/restaurant.sql")
    @DisplayName("요일로 운영정보 찾기")
    void findByRestaurantIdAndDayType(){

        final var timetable = restaurantTimeTableRepository.findByRestaurantIdAndDayType(1L, "MON");
        assertThat(timetable.isPresent()).isTrue();
        assertThat(timetable.get().getId()).isEqualTo(1);

    }

    @Test
    @Sql("classpath:db/restaurant.sql")
    @DisplayName("식당 운영정보 찾기")
    void findByRestaurantId(){
        final var timetable = restaurantTimeTableRepository.findAllByRestaurantId(1L);
        assertThat(timetable.isPresent()).isTrue();
        assertThat(timetable.get().size()).isEqualTo(1);
    }

}
