package com.sparta.delivery_hwk.repository;

import com.sparta.delivery_hwk.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String restaurantName);
}
