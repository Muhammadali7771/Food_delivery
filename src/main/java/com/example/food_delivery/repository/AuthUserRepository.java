package com.example.food_delivery.repository;

import com.example.food_delivery.entity.AuthUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer>  {

}
