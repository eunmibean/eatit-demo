package com.eatit.eatitapiv3.api.restaurant;

import com.eatit.eatitapiv3.api.restaurant.dto.RestaurantSummaryResponse;
import com.eatit.eatitapiv3.api.restaurant.dto.UpdateRestaurantRequest;
import com.eatit.eatitapiv3.application.restaurant.OwnerRestaurantService;
import com.eatit.eatitapiv3.domain.restaurant.Restaurant;
import com.eatit.eatitapiv3.domain.restaurant.RestaurantTimeTable;
import com.eatit.eatitapiv3.domain.user.BaseUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.eatit.eatitapiv3.mock.RestaurantMockCreator.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@WebMvcTest(OwnerRestaurantController.class)
public class OwnerRestaurantControllerTest {

    @MockBean
    private OwnerRestaurantService ownerRestaurantService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restaurantUpdate() throws Exception {
        UpdateRestaurantRequest request = createUpdateRequest();
        Restaurant restaurant = createRestaurant();
        Long restaurantId = 1L;
        when(ownerRestaurantService.update(any(Restaurant.class), anyLong())).thenReturn(restaurant);

        ObjectMapper mapper = new ObjectMapper();
        String contents = mapper.writeValueAsString(request);

        mockMvc.perform(patch("/v3/owner/restaurant/" + restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contents))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void restaurantUpdateCheckValidation() throws Exception {
        UpdateRestaurantRequest request = new UpdateRestaurantRequest(
                "Gelato가 조아요",
                "서울본점",
                "이탈리안 젤라또",
                null,
                null,
                null,
                0,
                Set.of("desert"),
                true
        );


        Restaurant restaurant = createRestaurant();
        Long restaurantId = 1L;
        when(ownerRestaurantService.update(any(Restaurant.class), anyLong())).thenReturn(restaurant);

        ObjectMapper mapper = new ObjectMapper();
        String contents = mapper.writeValueAsString(request);

        mockMvc.perform(patch("/v3/owner/restaurant/" + restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contents))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void restaurantTimeTableList() throws Exception {
        Long restaurantId = 1L;
        final var summaryResponse = createTimeTableSummaryResponse();
        when(ownerRestaurantService.getTimeTables(anyLong())).thenReturn(summaryResponse);

        MvcResult result = mockMvc.perform(get("/v3/owner/restaurant/{id}/timeTable", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Set<RestaurantTimeTable> restaurantTimeTables = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }

    @Test
    void restaurantUpdateTimeTable() throws Exception {
        Long restaurantId = 1L;
        final var timetableRequest = createTimeTableUpdateRequestSet();

        ObjectMapper mapper = new ObjectMapper();
        String contents = mapper.writeValueAsString(timetableRequest);

        mockMvc.perform(patch("/v3/owner/restaurant/{id}/timetable", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contents))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser")
    void myRestaurantList() throws Exception {
        final var user = new BaseUser();
        List<RestaurantSummaryResponse> response = Arrays.asList(createRestaurantSummaryResponse());
        when(ownerRestaurantService.readAllMyRestaurant(user)).thenReturn(response);

        MvcResult result = mockMvc.perform(get("/v3/owner/restaurant/my")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void restaurantMealTicketEnabled() throws Exception {
        final var restaurantId = 1L;
        final var mealTicketEnabled = true;

        mockMvc.perform(patch("/v3/owner/restaurant/{id}/mealTicketEnabled", restaurantId)
                        .param("mealTicketEnabled", String.valueOf(mealTicketEnabled))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }
}