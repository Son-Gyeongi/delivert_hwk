package com.sparta.delivery_hwk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor //RestaurantRequestDto에는 없는데 여기는 왜 있을까?
@NoArgsConstructor //RestaurantRequestDto에는 없는데 여기는 왜 있을까?
@Getter
public class FoodDto {

    private String name;  //foodName
    private int price;
}
