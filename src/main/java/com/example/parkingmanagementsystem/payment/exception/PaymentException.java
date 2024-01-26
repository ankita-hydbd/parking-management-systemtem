package com.example.parkingmanagementsystem.payment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PaymentException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public PaymentException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
