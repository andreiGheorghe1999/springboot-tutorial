package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class) // Se marcheaza tipul de exceptie care este triggeruit si handled prin metoda aceasta
    @ResponseStatus(HttpStatus.NOT_FOUND) // Se marcheaza tipul de raspuns returnat (statusul sau)
    public CustomErrorDetails usernameNotFound(UsernameNotFoundException exception){
        return new CustomErrorDetails(new Date(), "From RestControllerAdvice", exception.getMessage());
    }

}
