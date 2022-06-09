package com.sparta.delivery_hwk.model.order;
//음식 주문 테이블

import com.sparta.delivery_hwk.model.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class OrderFoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //테이블 고유값

    @Column(nullable = false)
    private int quantity; //음식수량

    @Column(nullable = false)
    private String name; //음식 이름

    @Column(nullable = false)
    private int price;

    //음식 ID를 받아와야겠지??? , deliveryFee도 여기서 받아오나?
    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    //주문 전체 불러오는 건가?
    @ManyToOne
    private Orders orders;
}
