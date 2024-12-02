package com.example.food_delivery.configuration;

import com.example.food_delivery.dto.BaseResponse;
import com.example.food_delivery.dto.ErrorData;
import com.example.food_delivery.exception.EmailAlreadyExists;
import com.example.food_delivery.exception.PhoneNumberAlreadyExists;
import com.example.food_delivery.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public BaseResponse<Void> tokenInvalid(AccessDeniedException e, HttpServletRequest request){
        ErrorData errorData = new ErrorData(e.getMessage(), request.getRequestURI());
        return new BaseResponse<>(errorData);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<ErrorData> handleInvalidArgument(MethodArgumentNotValidException e, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> {
                    String field = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    map.put(field, errorMessage);
                }
        );
        return new BaseResponse<>(new ErrorData("Not valid input", request.getRequestURI(), map));
    }

}
