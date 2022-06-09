package com.sparta.delivery_hwk.dto.order.response;

import com.sparta.delivery_hwk.model.order.Orders;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<FoodOrderResponseDto> foods; //foodsResponse
    private int deliveryFee;
    private int totalPrice;

    /**
     * 1.Orders 테이블에서 restaurantName, totalPrice 가져오기
     * 2. deliveryFee 받아서 넣기
     * 3. List<FoodsResponseDto>에서 받아서 foods에 넣기
     */
    public OrderResponseDto(Orders orders, int deliveryFee, List<FoodOrderResponseDto> foodsResponse) {
        this.restaurantName = orders.getRestaurantName();
        this.foods = foodsResponse;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }

}
