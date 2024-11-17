package com.example.food_delivery.configuration.security;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.ErrorData;
import com.example.food_delivery.exception.EmailAlreadyExists;
import com.example.food_delivery.exception.PhoneNumberAlreadyExists;
import com.example.food_delivery.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyExists.class)
    public BaseResponse<Void> emailAlreadyExists(EmailAlreadyExists e, HttpServletRequest request){
        ErrorData errorData = new ErrorData(e.getMessage(), request.getRequestURI());
        return new BaseResponse<>(errorData);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PhoneNumberAlreadyExists.class)
    public BaseResponse<Void> phoneNumberAlreadyExists(PhoneNumberAlreadyExists e, HttpServletRequest request){
        ErrorData errorData = new ErrorData(e.getMessage(), request.getRequestURI());
        return new BaseResponse<>(errorData);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseResponse<Void> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        ErrorData errorData = new ErrorData(e.getMessage(), request.getRequestURI());
        return new BaseResponse<>(errorData);
    }

}
