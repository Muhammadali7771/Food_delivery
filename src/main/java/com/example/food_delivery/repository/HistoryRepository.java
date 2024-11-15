package com.example.food_delivery.repository;

import com.example.food_delivery.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
