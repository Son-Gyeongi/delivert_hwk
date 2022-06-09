package com.sparta.delivery_hwk.controller;


import com.sparta.delivery_hwk.model.Restaurant;
import com.sparta.delivery_hwk.dto.RestaurantDto;
import com.sparta.delivery_hwk.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequiredArgsConstructor //이거와 아래 RestController 두 개 쓰면 new 어쩌고 저쩌꼬 ㅅ신경 안 써도 된다. 요청이 들어올 때 스프링이 다 알아서 해준다.
//@RestController   //다른 곳에 가서 생성이 되고 작업이 될때 스프링이 자동으로 생성해줌 / new RestaurantController 안써도 됨
@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class RestaurantController {

    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(@RequestBody RestaurantDto requestDto) {

        return restaurantService.addRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> allReadRestaurant() {
        return restaurantService.allReadRestaurant();
    }

}
