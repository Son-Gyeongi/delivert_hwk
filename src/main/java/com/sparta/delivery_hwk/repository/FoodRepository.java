package com.sparta.delivery_hwk.repository;

import com.sparta.delivery_hwk.model.Food;
import com.sparta.delivery_hwk.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    //1개의 음식점에서 중복되는 음식 이름 찾기
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);

    List<Food> findByRestaurant(Restaurant restaurant);
}
