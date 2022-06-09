package com.sparta.delivery_hwk.dto.order.response;

import com.sparta.delivery_hwk.model.order.OrderFoods;
import lombok.Getter;

@Getter
public class FoodOrderResponseDto {
    private String name; //foodName
    private int quantity;
    private int price;

    //OrderFoods에 저장된 값을 가져와서 FoodsResponseDto에 넣는다.
    //그리고 그걸 반환한다. <- 내 생각 내 입맛대로 변환
    public FoodOrderResponseDto(OrderFoods orderFoods) {
        this.name = orderFoods.getName();
        this.quantity = orderFoods.getQuantity();
        this.price = orderFoods.getPrice();
    }
}
