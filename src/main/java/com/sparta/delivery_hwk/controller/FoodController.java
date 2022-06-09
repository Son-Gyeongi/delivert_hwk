package com.sparta.delivery_hwk.controller;


import com.sparta.delivery_hwk.dto.FoodDto;
import com.sparta.delivery_hwk.model.Food;
import com.sparta.delivery_hwk.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor //이거와 아래 RestController 두 개 쓰면 new 어쩌고 저쩌꼬 ㅅ신경 안 써도 된다. 요청이 들어올 때 스프링이 다 알아서 해준다.
//@RestController   //다른 곳에 가서 생성이 되고 작업이 될때 스프링이 자동으로 생성해줌 / new FoodController 안써도 됨
@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class FoodController {

    private final FoodService foodService;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodRequestDtoList) {

        //foodService에 음식 등록하라고 요청하기
        foodService.addFood(restaurantId, foodRequestDtoList);

    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId) {
        return foodService.getMenu(restaurantId);
    }
}
