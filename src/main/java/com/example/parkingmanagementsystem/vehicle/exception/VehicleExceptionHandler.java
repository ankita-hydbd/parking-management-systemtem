package com.example.parkingmanagementsystem.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionHandler {
    @ExceptionHandler(value = {VehicleNotFoundException.class})
    public ResponseEntity<Object> VehicleNotFoundException
            (VehicleNotFoundException vehicleNotFoundException)
    {
         VehicleException VehicleException;
        VehicleException = new VehicleException(vehicleNotFoundException.getMessage() ,vehicleNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(VehicleException,HttpStatus.NOT_FOUND);
    }



}
