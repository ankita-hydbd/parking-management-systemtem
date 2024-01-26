package com.example.parkingmanagementsystem.parking.floor.exception;

public class ParkingFloorNotFoundException extends RuntimeException {
    public ParkingFloorNotFoundException(String message) {
        super(message);
    }

    public ParkingFloorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
