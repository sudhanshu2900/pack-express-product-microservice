package com.sg.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sg.product.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class MyControllerAdvice {
    Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);
    /*
     * It handle the custom error messages at the backend side
     */

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex, WebRequest wr){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage(ex.getMessage());
        logger.error("Error:{}",errorResponse);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest wr){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage(ex.getMessage());
        logger.error("Error:{}",errorResponse);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderStatusNotFoundException(OrderStatusNotFoundException ex, WebRequest wr){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(404);
        errorResponse.setMessage(ex.getMessage());
        logger.error("Error:{}",errorResponse);
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

}