package com.sparta.delivery_hwk.service;

import com.sparta.delivery_hwk.dto.FoodDto;
import com.sparta.delivery_hwk.model.Food;
import com.sparta.delivery_hwk.model.Restaurant;
import com.sparta.delivery_hwk.repository.FoodRepository;
import com.sparta.delivery_hwk.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.sparta.delivery_hwk.exception.ExceptionMessages.*;


@RequiredArgsConstructor  //RestaurantRepostitory 자동 생성 /업데이트하려면 필수 어노테이셔(@), //repository가 외부에서 service로 넣어주게 생성자 생성
@Service
public class FoodService {
    
    private final FoodRepository foodRepository;
    
    private final RestaurantRepository restaurantRepository;
    
    //음식 등록
    @Transactional
    public void addFood(Long restaurantId, List<FoodDto> foodRequestDtoList) {
        //등록되지 않은 음식점
        Optional<Restaurant> targetRestaurant = restaurantRepository.findById(restaurantId);

        checkRestaurant(targetRestaurant);
        Restaurant restaurant = targetRestaurant.get(); //찾은 음식점 restaurant 객체에 다 넣음

        for (FoodDto requestDto : foodRequestDtoList){
            //foodRequestDtoList를 하나씩 requestDto에 넣겠다? 맞나?

            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            //요청받은 값 다시 정리해서 넣기
            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }
    }



    //레스토랑이 등록되어 있지 않을 때
    private void checkRestaurant(Optional<Restaurant> targetRestaurant) {
        if(!targetRestaurant.isPresent())   //레스토랑이 존재하지 않으면 참
            throw new IllegalArgumentException(RESTAURANT_IS_NULL);
    }


    /**
     * 같은 음식점 내에서는 음식 이름이 중복될 수 없음 - //1개 음식점 안에 같은 메뉴 이름 안됨
     * (예. '자담치킨 강남점'에 '후라이드치킨' 이 이미 등록되어 있다면 중복하여 등록할 수 없지만,
     * 다른 음식점인 '맘스터치 강남점'에는 '후라이드치킨' 을 등록 가능)
     */
    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName) {
        //레스토랑과 음식이름으로 검색해서 찾기
        //restaurant와 foddName 비교 하려고 파라미터에 가져옴
        Optional<Food> duplicateFoodName = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        //restaurant와 foodName을 가지고 어떻게 찾지???
        if(duplicateFoodName.isPresent())
            throw new IllegalArgumentException(RESTAURANT_FOOD_DUPLICATE);
    }

    /**
     * 1. 허용값: 100원 ~ 1,000,000원
     * 2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
     * 3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
     */
    private void checkFoodPrice(int foodPrice) {

        if (foodPrice < 100)
            throw new IllegalArgumentException(FOOD_PRICES_TOO_LOW);

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException(FOOD_PRICES_TOO_HIGH);

        if(foodPrice % 100 > 0){
            throw new IllegalArgumentException(ILLEGAL_FOOD_PRICES_UNIT);
        }

    }

    //메뉴판 조회
    @Transactional
    public List<Food> getMenu(Long restaurantId) {
        //레스토랑 id로 메뉴판 찾기
        //id로 레스토랑 있는지 확인하기
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException(RESTAURANT_IS_NULL));


        return foodRepository.findByRestaurant(restaurant);

    }
}
