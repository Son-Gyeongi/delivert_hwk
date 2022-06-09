# delivery_hwk
항해99 스프링 심화주차 과제

> [yarogono/DeliveryApi](https://github.com/yarogono/DeliveryApi) - 참고한 깃허브

--------------------------
## 1. 음식점 등록 및 조회
### 요구사항
- 음식점 정보 입력받아 등록
    1. 음식점 이름 (name)
    2. 최소주문 가격 (minOrderPrice)
        1. 허용값: 1,000원 ~ 100,000원 입력
        2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
    3. 기본 배달비 (deliveryFee)
        1. 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
        2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능) 
        
- 음식점 조회
    - 등록된 모든 음식점 정보 조회 가능
        1. 등록 시 입력한 음식점 정보 (name, minOrderPrice, deliveryFee)
        2. DB 테이블 ID (id)

### API 명세서
![image](https://user-images.githubusercontent.com/78200199/172749405-2ddb93d4-aa8a-411f-b322-90da3ff9c6e2.png)

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
### 요구사항
- 음식점 ID 및 음식 정보 입력받아 등록
    1. 음식점 ID (restaurantId)
        1. 음식점 DB 테이블 ID
    2. 음식명 (name)
        1. 같은 음식점 내에서는 음식 이름이 중복될 수 없음 
        2. (예. '자담치킨 강남점'에 '후라이드치킨' 이 이미 등록되어 있다면 중복하여 등록할 수 없지만, 다른 음식점인 '맘스터치 강남점'에는 '후라이드치킨' 을 등록 가능)
    3. 가격 (price)
        1. 허용값: 100원 ~ 1,000,000원
        2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
        3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
        
- 메뉴판 조회
    - 하나의 음식점에 등록된 모든 음식 정보 조회
        1. 등록 시 입력한 음식 정보 (name, price)
        2. DB 테이블 ID (id)

### API 명세서
![image](https://user-images.githubusercontent.com/78200199/172749678-1aac4c35-2a88-4d8b-94fc-11b3200dd0e1.png)
![image](https://user-images.githubusercontent.com/78200199/172749731-046eb893-a242-424a-b0da-cdd119dfef09.png)

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
### 요구사항
- 주문 요청 시 배달 음식점 및 음식 정보 입력받음
    1. 음식점 ID (restaurantId)
    2. 음식 주문 정보 (foods)
        1. 음식 ID (id)
        2. 음식을 주문할 수량 (quantity)
            1. 허용값: 1 ~ 100
            2. 허용값이 아니면 에러 발생시킴
            
- 주문 요청에 대한 응답으로 다음 정보를 포함시킴
    1. 주문 음식점 이름 (restaurantName)
    2. 주문 음식 정보 (foods)
        - 주문 음식명 (name)
        - 주문 수량 (quantity)
        - 주문 음식의 가격 (price)
            - 계산방법
                - 주문 음식 1개의 가격 * 주문 수량
    3. 배달비 (deliveryFee)
        - 주문 음식점의 기본 배달비
    4. 최종 결제 금액 (totalPrice)
        - 계산방법
            - 주문 음식 가격들의 총 합 + 배달비
        - "주문 음식 가격들의 총 합" 이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생시킴
        
- 주문 조회
    - 그동안 성공한 모든 주문 요청을 조회 가능

### API 명세서
![image](https://user-images.githubusercontent.com/78200199/172749831-11e4344f-2ff1-4997-85cc-ef270e5cf5b1.png)
![image](https://user-images.githubusercontent.com/78200199/172749878-6950eca9-ca6a-4b48-9f07-6d44df0b37e7.png)


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

- RestaurantIntegrationTest
- ![image](https://user-images.githubusercontent.com/78200199/172751496-8885ec5b-c148-494f-b8df-394bdb7e6d75.png)

- FoodIntegrationTest.java
- ![image](https://user-images.githubusercontent.com/78200199/172751586-012a7cec-5884-4b7d-843b-ca7054033399.png)

- OrderIntegrationTest.java
- ![image](https://user-images.githubusercontent.com/78200199/172751672-359bca17-229d-48bd-834e-dcbacc58bc6e.png)

