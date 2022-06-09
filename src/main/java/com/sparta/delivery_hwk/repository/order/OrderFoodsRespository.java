package com.sparta.delivery_hwk.repository.order;

import com.sparta.delivery_hwk.model.order.OrderFoods;
import com.sparta.delivery_hwk.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodsRespository extends JpaRepository<OrderFoods, Long> {

    List<OrderFoods> findOrderFoodsByOrders(Orders orders);
}
