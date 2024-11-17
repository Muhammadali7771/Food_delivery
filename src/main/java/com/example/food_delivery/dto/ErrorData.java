package com.example.food_delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorData(String message, String path, Object params) {
    public ErrorData(String message, String path){
        this(message, path, null);
    }
}
