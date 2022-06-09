package com.sparta.delivery_hwk.service;


import com.sparta.delivery_hwk.model.Restaurant;
import com.sparta.delivery_hwk.dto.RestaurantDto;
import com.sparta.delivery_hwk.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.sparta.delivery_hwk.exception.ExceptionMessages.*;

//@Transactional //jpa 쓸 때는 항상 @Transactional이 있어야 한다.(데이터 저장하거나 변경할 때 필요)
@RequiredArgsConstructor  //RestaurantRepostitory 자동 생성 /업데이트하려면 필수 어노테이셔(@), //repository가 외부에서 service로 넣어주게 생성자 생성
@Service //스프링에게 RestaurantService가 서비스인거 알려준다. /업데이트하려면 필수 어노테이셔(@)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    //음식점 등록
    @Transactional   //DB에 진짜 반영이 되어야해!! /업데이트하려면 필수 어노테이셔(@)
    public Restaurant addRestaurant(RestaurantDto requestDto) {
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        /**
         * minOrderPrice 최소 주문금액, deliveryFee 배달비 제한기능 넣기
         */
        checkMinOrderPrice(minOrderPrice);

        checkDeliveryFee(deliveryFee);


        //요청받은 값 다시 정리해서 넣기
        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();


        restaurantRepository.save(restaurant);

        return restaurant;
    }


    /**
     * 1. 허용값: 1,000원 ~ 100,000원 입력
     * 2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
     * 3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
     */
    private void checkMinOrderPrice(int minOrderPrice) {
        if(!(1000 <= minOrderPrice && minOrderPrice <= 100000)) {
            throw new IllegalArgumentException(ILLEGAL_MIN_ORDER_PRICE_RANGE);
        }

        if(minOrderPrice % 100 > 0) {
            throw new IllegalArgumentException(ILLEGAL_MIN_ORDER_PRICE_UNIT);
        }
    }

    /**
     * 1. 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
     * 2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능)
     */
    private void checkDeliveryFee(int deliveryFee) {
        if(!(0 <= deliveryFee && deliveryFee <= 10000)) {
            throw new IllegalArgumentException(ILLEGAL_DELIVERY_FEE_RANGE);
        }

        if(deliveryFee % 500 > 0) {
            throw new IllegalArgumentException(ILLEGAL_DELIVERY_FEE_UNIT);
        }
    }

    public List<Restaurant> allReadRestaurant() {
        return restaurantRepository.findAll();
    }
}
