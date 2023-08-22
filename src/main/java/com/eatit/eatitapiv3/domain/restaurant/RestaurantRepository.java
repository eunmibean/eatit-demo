package com.eatit.eatitapiv3.domain.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant>  findAllByOwnerId(Long ownerId);
}

