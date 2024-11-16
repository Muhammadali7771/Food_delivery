package com.example.food_delivery.repository;

import com.example.food_delivery.entity.AuthUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer>  {
    Optional<AuthUser> findByEmail(String email);
}
