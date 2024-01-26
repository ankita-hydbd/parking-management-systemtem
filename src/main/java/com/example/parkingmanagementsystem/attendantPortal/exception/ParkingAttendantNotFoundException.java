package com.example.parkingmanagementsystem.attendantPortal.exception;

public class ParkingAttendantNotFoundException extends RuntimeException {
    public ParkingAttendantNotFoundException(String message) {
        super(message);
    }

    public ParkingAttendantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
