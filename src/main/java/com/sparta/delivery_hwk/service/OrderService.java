package com.sparta.delivery_hwk.service;

import com.sparta.delivery_hwk.dto.order.request.OrderRequestDto;
import com.sparta.delivery_hwk.dto.order.response.FoodOrderResponseDto;
import com.sparta.delivery_hwk.dto.order.response.OrderResponseDto;
import com.sparta.delivery_hwk.model.Food;
import com.sparta.delivery_hwk.model.Restaurant;
import com.sparta.delivery_hwk.model.order.OrderFoods;
import com.sparta.delivery_hwk.model.order.Orders;
import com.sparta.delivery_hwk.repository.FoodRepository;
import com.sparta.delivery_hwk.repository.RestaurantRepository;
import com.sparta.delivery_hwk.repository.order.OrderFoodsRespository;
import com.sparta.delivery_hwk.repository.order.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.sparta.delivery_hwk.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderFoodsRespository orderFoodsRespository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    //주문하기
    @Transactional  //업데이트할 때 DB에 저장하겠다는 말
    public OrderResponseDto orderFoods(OrderRequestDto orderRequestDto) {
        //받은 주문 dto -> Entity로 바꾸기

        //레스토랑 존재하는 지 확인하는 getRestaurant메서드
        Restaurant restaurant = getRestaurant(orderRequestDto);

        //변수 선언
        //음식 총합 초기화
        int totalPrice = 0;
        //ArrayList에 request에서 받은 음식 리스트 가져오기
        List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();
        //주문 받은 요청 Dto에서 Foods 객체 들고오기
        List<OrderFoods> orderFoods = orderRequestDto.getFoods();
        //orderFoodsList 객체 리스트 선언
        List<OrderFoods> orderFoodsList = new ArrayList<>();

        //가져 온 리스트 객체 하나씩 넣기
        for(OrderFoods tempOrderFoods : orderFoods){
            //DTO에 있는 거 Entity로 바꾸기?? 맞나? 아닌거 같은데

            /**
             * - 음식을 주문할 수량 (quantity)
             *     1. 허용값: 1 ~ 100
             *     2. 허용값이 아니면 에러 발생시킴
             */
            int quantity = tempOrderFoods.getQuantity();
            if(quantity <1 || quantity >100) {
                throw new IllegalArgumentException(ILLEGAL_FOOD_ORDER_QUANTITY);
            }

            //OrderFoods 객체에 주문 받은 음식이 존재 여부 검사
            Food food = getFood(tempOrderFoods);

            //검사한 것들 Entity로 바꿔서 저장하자
            OrderFoods orderFood = OrderFoods.builder()
                    .name(food.getName())
                    .quantity(tempOrderFoods.getQuantity())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            //주문하기 테이블에 저장
            orderFoodsRespository.save(orderFood);

            //위에 저장한 orderFood 주문한거 저장하는 거를
            //반환하는 FoodOrderResponseDto에 저장
            FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(orderFood);

            //반환하는 음식 주문 리스트에 저장하기
            foodOrderResponseDtoList.add(foodOrderResponseDto);

            //total 가격 계산하기
            totalPrice += food.getPrice() * quantity;

            //전체적으로 반환하는 주문하기 리스트에 넣기
            orderFoodsList.add(orderFood);

        }// 일단 받아온 값 저장하는 거 for뮨 끝

        /**
         * 최종 결제 금액 (totalPrice)
         *  - 계산방법
         *      - 주문 음식 가격들의 총 합 + 배달비
         *  - "주문 음식 가격들의 총 합" 이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생시킴
         */
        if(totalPrice<restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException(ILLEGAL_TOTAL_PRICE);
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        //주문 받은 거 다시 정리해서 orderRepository에 저장
        Orders orders = Orders.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(orderFoodsList)
                .build();
        //주문 받은 거 orderRepository에 저장하기
        ordersRepository.save(orders);

        //orders와 deliveryFee, foods, foodOrderResponseDtoList 만든 거
        //반환하는 OrderResponseDto에 넣고 반환하기
        OrderResponseDto orderResponseDto = new OrderResponseDto(orders,deliveryFee, foodOrderResponseDtoList);
        return orderResponseDto;
    }


    //레스토랑 존재 여부
    private Restaurant getRestaurant(OrderRequestDto requestDto) {
        Restaurant restaurant= restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException(RESTAURANT_IS_NULL)
                );
        return restaurant;
    }

    //OrderFoods 객체에 주문 받은 음식이 존재하지 않는다면?
    private Food getFood(OrderFoods tempOrderFoods) {
        return foodRepository.findById(tempOrderFoods.getId())
                .orElseThrow(() -> new NullPointerException(CANT_FIND_FOOD));
    }


    //모든 주문 요청 조회
    public List<OrderResponseDto> getOrders() {
        //OrderResponseDto를 리스트로 보내야하니깐 리스트 객체 생성
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        //주문하기 테이블에서 이때까지 한 거를 모두 찾자
        List<Orders> ordersList= ordersRepository.findAll();

        for(Orders orders : ordersList) {
            //누가 주문 했는지 음식점 이름으로 찾기
            //restaurantRepository에서 이름으로 검색해서 배달비 들고오기
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            
            //주문한 음식 리스트 가져오려고 선언
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

            //orderFoodsRespository에서 OrderFoods 테이블에서
            //Orders 연결된 테이블 들고온다. (주문 받은 테이블에 레스토랑 이름도 있는거)
            List<OrderFoods> orderFoodsList = orderFoodsRespository.findOrderFoodsByOrders(orders);

            //받아온 주문 리스트 - 얘는 음식들 리스틑 담는 거
            for(OrderFoods orderFoods : orderFoodsList) {
                FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(orderFoods);
                foodOrderResponseDtoList.add(foodOrderResponseDto);
            }

            //반환할 때 넣어서 보내줘야할 것들
            OrderResponseDto orderResponseDto = new OrderResponseDto(orders, deliveryFee, foodOrderResponseDtoList);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
