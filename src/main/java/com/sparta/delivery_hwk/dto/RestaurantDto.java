package com.sparta.delivery_hwk.dto;


import lombok.Getter;


@Getter    //멤버변수 값 읽는 Getter
public class RestaurantDto {
    //필요한 정보를 계속 들고다니는 아이
    //네 가지 멤버변수 넣고 기본적으로 private선언 해주자
//    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
