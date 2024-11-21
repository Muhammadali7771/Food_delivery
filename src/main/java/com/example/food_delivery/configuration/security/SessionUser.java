package com.example.food_delivery.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SessionUser {
    public CustomUserDetails user(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails user){
            return user;
        }
        return null;
    }

    public Integer getId(){
        CustomUserDetails user = user();
        if (Objects.isNull(user)){
            return -1;
        }
        return user.getId();
    }

}
