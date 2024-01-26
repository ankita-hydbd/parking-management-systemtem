package com.example.parkingmanagementsystem.vehicle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VehicleException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public VehicleException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
