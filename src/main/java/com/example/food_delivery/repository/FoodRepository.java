package com.example.food_delivery.repository;

import com.example.food_delivery.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("select f from Food f where f.category.id = ?1")
    List<Food> findAllByCategoryId(Integer categoryId);

    List<Food> findAllByNameContainingIgnoreCase(String item);

    @Query("""
                   SELECT f
                       FROM Food f
                       LEFT JOIN OrderItem oi ON oi.food.id = f.id
                       GROUP BY f.id
                       ORDER BY COUNT(oi.id) DESC
            """)
    List<Food> findFoodListByPopularity();
}
