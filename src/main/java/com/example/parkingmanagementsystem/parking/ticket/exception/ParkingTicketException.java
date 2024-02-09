package com.example.parkingmanagementsystem.parking.ticket.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParkingTicketException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public ParkingTicketException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
