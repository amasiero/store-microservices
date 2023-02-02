package com.andreymasiero.cart.exception.advice;

import java.time.LocalDateTime;

import com.andreymasiero.dtos.error.ErrorDto;
import com.andreymasiero.exceptions.products.ProductNotFoundException;
import com.andreymasiero.exceptions.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {
    "com.andreymasiero.cart.controllers"
})
public class CartControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDto handleProductNotFound(ProductNotFoundException productNotFoundException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("Product not found.");
        errorDto.setTimestamp(LocalDateTime.now());
        return errorDto;
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDto handleUserNotFound(UserNotFoundException userNotFoundException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("User not found.");
        errorDto.setTimestamp(LocalDateTime.now());
        return errorDto;
    }
}
