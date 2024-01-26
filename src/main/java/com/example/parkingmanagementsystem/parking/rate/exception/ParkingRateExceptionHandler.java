package com.example.parkingmanagementsystem.parking.rate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingRateExceptionHandler {
    @ExceptionHandler(value = {ParkingRateNotFoundException.class})
    public ResponseEntity<Object> ParkingTicketNotFoundException
            (ParkingRateNotFoundException ParkingRateNotFoundException)
    {
         ParkingRateException VehicleException;
        VehicleException = new ParkingRateException(ParkingRateNotFoundException.getMessage() ,ParkingRateNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(VehicleException,HttpStatus.NOT_FOUND);
    }



}
