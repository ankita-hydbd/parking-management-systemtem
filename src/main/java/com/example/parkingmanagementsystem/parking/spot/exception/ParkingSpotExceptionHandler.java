package com.example.parkingmanagementsystem.parking.spot.exception;

import com.example.parkingmanagementsystem.parking.lot.exception.ParkingLotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingSpotExceptionHandler {
    @ExceptionHandler(value = {ParkingSpotNotFoundException.class})
    public ResponseEntity<Object> ParkingSpotNotFoundException
            (ParkingSpotNotFoundException parkingSpotNotFoundException)
    {
     ParkingLotException ParkingLotException;
        ParkingSpotException ParkingSpotException = new ParkingSpotException(parkingSpotNotFoundException.getMessage(), parkingSpotNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ParkingSpotException,HttpStatus.NOT_FOUND);
    }



}
