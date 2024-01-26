package com.example.parkingmanagementsystem.parking.ticket.exception;

public class ParkingTicketNotFoundException extends RuntimeException {
    public ParkingTicketNotFoundException(String message) {
        super(message);
    }

    public ParkingTicketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
