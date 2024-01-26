package com.example.parkingmanagementsystem.parking.rate.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParkingRateException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ParkingRateException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
