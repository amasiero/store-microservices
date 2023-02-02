package com.andreymasiero.products.exception.advice;

import java.time.LocalDateTime;
import java.util.List;

import com.andreymasiero.dtos.error.ErrorDto;
import com.andreymasiero.exceptions.products.CategoryNotFoundException;
import com.andreymasiero.exceptions.products.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {
    "com.andreymasiero.products.controllers"
})
public class ProductControllerAdvice {
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
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDto handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage("Category not found.");
        errorDto.setTimestamp(LocalDateTime.now());
        return errorDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto processValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder("Invalid values for: ");

        fieldErrors.forEach(field -> sb.append(field.getField()).append(","));
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }

        errorDto.setMessage(sb.toString());
        errorDto.setTimestamp(LocalDateTime.now());
        return errorDto;
    }
}
