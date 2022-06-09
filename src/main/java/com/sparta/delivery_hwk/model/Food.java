package com.sparta.delivery_hwk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder  //왜 필요하지? - @Service에서 요청받은 값을 빌드한다.
@AllArgsConstructor  //왜 필요하지?
@NoArgsConstructor    //클래스 Restaurant에 기본생성자가 없다. 그래서 기본 생성자 생성하는 lombok쓰자
@Getter
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //food 테이블 아이디

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;


    //음식점 아이디를 받아와야함
    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    private Restaurant restaurant;


}
