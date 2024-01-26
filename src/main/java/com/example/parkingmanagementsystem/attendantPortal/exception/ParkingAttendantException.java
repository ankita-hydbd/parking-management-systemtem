package com.example.parkingmanagementsystem.attendantPortal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParkingAttendantException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ParkingAttendantException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
