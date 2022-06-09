package com.sparta.delivery_hwk.dto.order.request;

import lombok.Getter;

@Getter
public class FoodOrderRequestDto {
    private Long id; //foodId
    private int quantity;
}

/**
 * FoodOrderRequestDto foodOrderRequest3 = FoodOrderRequestDto.builder()
 *                 .id(food3.id)
 *                 .quantity(3)
 *                 .build();
 */