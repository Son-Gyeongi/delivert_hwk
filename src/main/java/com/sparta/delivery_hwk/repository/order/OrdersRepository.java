package com.sparta.delivery_hwk.repository.order;

import com.sparta.delivery_hwk.model.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
