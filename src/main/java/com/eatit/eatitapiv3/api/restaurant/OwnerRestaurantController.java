package com.eatit.eatitapiv3.api.restaurant;

import com.eatit.eatitapiv3.api.restaurant.dto.*;
import com.eatit.eatitapiv3.application.restaurant.OwnerRestaurantService;
import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import com.eatit.eatitapiv3.domain.user.BaseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/owner/restaurant")
public class OwnerRestaurantController {

    private final OwnerRestaurantService ownerRestaurantService;

    /**
     * 식당 정보 수정 API
     *
     * @param id                      the restaurant id
     * @param updateRestaurantRequest the update restaurant request
     * @return the response entity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> restaurantUpdate(@PathVariable Long id, @RequestBody UpdateRestaurantRequest updateRestaurantRequest) {
        final var restaurant = ownerRestaurantService.update(updateRestaurantRequest.toRestaurant(), id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    /**
     * 영업상태 수정 API (영업중, 영업종료)
     *
     * @param id     the id
     * @param status the status
     * @return the response entity
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Restaurant> restaurantUpdateStatus(@PathVariable Long id, @RequestParam Boolean status) {
        final var restaurant = ownerRestaurantService.updateStatus(id, status);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    /**
     * 식당 운영정보 조회 API
     *
     * @param id the restaurant id
     * @return the response entity
     */
    @GetMapping("/{id}/timetable")
    public ResponseEntity<Set<TimeTableSummaryResponse>> restaurantTimeTableList(@PathVariable Long id) {
        final var restaurantTimeTables = ownerRestaurantService.getTimeTables(id);
        return new ResponseEntity<>(restaurantTimeTables, HttpStatus.OK);
    }


    /**
     * 운영 정보 수정하는 API
     *
     * @param id                     the restaurant id
     * @param timeTableUpdateRequest the update timetable request
     * @return the response entity
     */
    @PatchMapping("/{id}/timetable")
    public ResponseEntity<Void> restaurantUpdateTimeTables(@PathVariable Long id, @RequestBody Set<TimeTableUpdateRequest> timeTableUpdateRequest) {
        ownerRestaurantService.updateTimeTables(id, timeTableUpdateRequest);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    /**
     * 사장님 별 식당 조회 API
     *
     * @param user the user
     * @return the response entity
     */
    @GetMapping("/my")
    public ResponseEntity<List<RestaurantSummaryResponse>> myRestaurantList(@AuthenticationPrincipal BaseUser user) {
        final var result = ownerRestaurantService.readAllMyRestaurant(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 식당 식권 사용 여부 수정 API
     * @param id
     * @param mealTicketEnabled
     * @return
     */
    @PatchMapping("/{id}/mealTicketEnabled")
    public ResponseEntity<Void> restaurantMealTicketEnabled(@PathVariable Long id, @RequestParam Boolean mealTicketEnabled){
        ownerRestaurantService.updateMealTicketEnabled(id, mealTicketEnabled);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
