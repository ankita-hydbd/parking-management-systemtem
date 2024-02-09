package com.example.parkingmanagementsystem.parking.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingTicketExceptionHandler {
    @ExceptionHandler(value = {ParkingTicketNotFoundException.class})
    public ResponseEntity<Object> ParkingTicketNotFoundException
            (ParkingTicketNotFoundException ParkingTicketNotFoundException)
    {
         ParkingTicketException VehicleException;
        VehicleException = new ParkingTicketException(ParkingTicketNotFoundException.getMessage() ,ParkingTicketNotFoundException.getCause() , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(VehicleException,HttpStatus.NOT_FOUND);
    }



}
