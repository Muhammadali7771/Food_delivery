package com.example.food_delivery.repository;

import com.example.food_delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.authUser.id = ?1")
    List<Order> findOrderAllByAuthUserId(Integer id);
}
