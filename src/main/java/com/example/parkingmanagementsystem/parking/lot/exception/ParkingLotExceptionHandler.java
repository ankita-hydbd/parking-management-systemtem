package com.example.parkingmanagementsystem.parking.lot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ParkingLotExceptionHandler {
    @ExceptionHandler(value = {ParkingLotNotFoundException.class})
    public ResponseEntity<Object> ParkingLotNotFoundException
            (ParkingLotNotFoundException parkingLotNotFoundException)
    {
     ParkingLotException ParkingLotException;
        ParkingLotException = new ParkingLotException(parkingLotNotFoundException.getMessage() ,parkingLotNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ParkingLotException,HttpStatus.NOT_FOUND);
    }



}
