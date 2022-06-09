# delivert_hwk
항해99 스프링 심화주차 과제

--------------------------
## 1. 음식점 등록 및 조회
### dto
- [RestaurantDto.java](/src/main/java/com/sparta/delivery_hwk/dto/RestaurantDto.java)
### model
- [Restaurant.java](/src/main/java/com/sparta/delivery_hwk/model/Restaurant.java)
### controller
- [RestaurantController.java](/src/main/java/com/sparta/delivery_hwk/controller/RestaurantController.java)
### service
- [RestaurantService.java](/src/main/java/com/sparta/delivery_hwk/service/RestaurantService.java)
### repository
- [RestaurantRepository.java](/src/main/java/com/sparta/delivery_hwk/repository/RestaurantRepository.java)

---------------------------
## 2. 음식 등록 및 메뉴판 조회
### dto
- [FoodDto.java](/src/main/java/com/sparta/delivery_hwk/dto/FoodDto.java)
### model
- [Food.java](/src/main/java/com/sparta/delivery_hwk/model/Food.java)
### controller
- [FoodController.java](/src/main/java/com/sparta/delivery_hwk/controller/FoodController.java)
### service
- [FoodService.java](/src/main/java/com/sparta/delivery_hwk/service/FoodService.java)
### repository
- [FoodRepository.java](/src/main/java/com/sparta/delivery_hwk/repository/FoodRepository.java)

---------------------------
## 3. 주문 요청하기 및 주문 조회
### dto
- order/request [OrderRequestDto.java](/src/main/java/com/sparta/delivery_hwk/dto/order/request/OrderRequestDto.java)
- order/request [FoodOrderRequestDto.java](/src/main/java/com/sparta/delivery_hwk/dto/order/request/FoodOrderRequestDto.java)
- order/response [OrderResponseDto.java](/src/main/java/com/sparta/delivery_hwk/dto/order/response/OrderResponseDto.java)
- order/response [FoodOrderResponseDto.java](/src/main/java/com/sparta/delivery_hwk/dto/order/response/FoodOrderResponseDto.java)
### model
- [Orders.java](/src/main/java/com/sparta/delivery_hwk/model/order/Orders.java)
- [OrderFoods.java](/src/main/java/com/sparta/delivery_hwk/model/order/OrderFoods.java)
### controller
- [OrderController.java](/src/main/java/com/sparta/delivery_hwk/controller/OrderController.java)
### service
- [OrderService.java](/src/main/java/com/sparta/delivery_hwk/service/OrderService.java)
### repository
- [OrdersRepository.java](/src/main/java/com/sparta/delivery_hwk/repository/order/OrdersRepository.java)
- [OrderFoodsRespository.java](/src/main/java/com/sparta/delivery_hwk/repository/order/OrderFoodsRespository.java)

---------------------------
> [ExceptionMessages.java](/src/main/java/com/sparta/delivery_hwk/exception/ExceptionMessages.java) - 코드 속 메시지 모음

## 테스트 코드
- [RestaurantIntegrationTest.java](/src/test/java/com/sparta/delivery_hwk/RestaurantIntegrationTest.java)
- [FoodIntegrationTest.java](/src/test/java/com/sparta/delivery_hwk/FoodIntegrationTest.java)
- [OrderIntegrationTest.java](/src/test/java/com/sparta/delivery_hwk/OrderIntegrationTest.java)
