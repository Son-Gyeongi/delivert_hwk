package com.sparta.delivery_hwk.controller;

import com.sparta.delivery_hwk.dto.order.request.OrderRequestDto;
import com.sparta.delivery_hwk.dto.order.response.OrderResponseDto;
import com.sparta.delivery_hwk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문하기
    @PostMapping("/order/request")
    public OrderResponseDto orderFoods(@RequestBody OrderRequestDto requestDto) {

        return orderService.orderFoods(requestDto);
    }

    //주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders() {

        return orderService.getOrders();
    }
}
