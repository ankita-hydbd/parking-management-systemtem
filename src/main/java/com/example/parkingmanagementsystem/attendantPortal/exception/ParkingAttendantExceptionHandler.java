package com.example.parkingmanagementsystem.attendantPortal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingAttendantExceptionHandler {
    @ExceptionHandler(value = {ParkingAttendantNotFoundException.class})
    public ResponseEntity<Object> ParkingAttendantNotFoundException
            (ParkingAttendantNotFoundException ParkingAttendantNotFoundException)
    {
         ParkingAttendantException VehicleException;
        VehicleException = new ParkingAttendantException(ParkingAttendantNotFoundException.getMessage() ,ParkingAttendantNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(VehicleException,HttpStatus.NOT_FOUND);
    }



}
