package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

//@ControllerAdvice
public class CustomGlobalExceptionHandlerr extends ResponseEntityExceptionHandler {

    // Definitia metodelor o iau din ResponseEntityExceptionHandler si doar definesc custom behaviour.
    // MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "From MethodArgumentNotValidException in GEH", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    // HttpRequestMethodNotSupported
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "From HttpRequestMethodNotSupported in GEH", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Custom exception -- UsernameNotFoundException
    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }
    // ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

}
