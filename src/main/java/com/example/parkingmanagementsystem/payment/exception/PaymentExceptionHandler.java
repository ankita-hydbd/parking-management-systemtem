package com.example.parkingmanagementsystem.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentExceptionHandler {
    @ExceptionHandler(value = {PaymentNotFoundException.class})
    public ResponseEntity<Object> PaymentNotFoundException
            (PaymentNotFoundException paymentNotFoundException)
    {
         PaymentException VehicleException;
        VehicleException = new PaymentException(paymentNotFoundException.getMessage() ,paymentNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(VehicleException,HttpStatus.NOT_FOUND);
    }



}
