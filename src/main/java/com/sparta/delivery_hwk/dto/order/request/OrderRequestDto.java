package com.sparta.delivery_hwk.dto.order.request;

import com.sparta.delivery_hwk.model.order.OrderFoods;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;

    private List<OrderFoods> foods;
}

/**
 * OrderRequestDto orderRequest = OrderRequestDto.builder()
 *                 .restaurantId(restaurantId)
 *                 .foods(foodOrderRequestDtos)
 *                 .build();
 */
