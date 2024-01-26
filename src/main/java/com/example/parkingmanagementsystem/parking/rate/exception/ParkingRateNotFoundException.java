package com.example.parkingmanagementsystem.parking.rate.exception;

public class ParkingRateNotFoundException extends RuntimeException {
    public ParkingRateNotFoundException(String message) {
        super(message);
    }

    public ParkingRateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
