package com.example.parkingmanagementsystem.parking.floor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingFloorExceptionHandler {
    @ExceptionHandler(value = {ParkingFloorNotFoundException.class})
    public ResponseEntity<Object> ParkingFloorNotFoundException
            (ParkingFloorNotFoundException parkingFloorNotFoundException)
    {
         ParkingFloorException ParkingFloorException;
        ParkingFloorException = new ParkingFloorException(parkingFloorNotFoundException.getMessage() ,parkingFloorNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ParkingFloorException,HttpStatus.NOT_FOUND);
    }



}
