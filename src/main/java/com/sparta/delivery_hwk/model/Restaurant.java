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
@Entity       //나 단순한 클래스가 아니라 데이터베이스를 위해서 쓰이는 녀석이다
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 알아서 생성해주는 걸 IDENTITY라고 한다.
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;
}
