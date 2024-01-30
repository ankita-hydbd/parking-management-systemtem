package com.example.parkingmanagementsystem.parking.spot.exception;

public class ParkingSpotNotFoundException extends RuntimeException {
    public ParkingSpotNotFoundException(String message) {
        super(message);
    }

    public ParkingSpotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
