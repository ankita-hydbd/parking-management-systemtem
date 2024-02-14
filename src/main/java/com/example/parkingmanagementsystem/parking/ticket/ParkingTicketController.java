package com.example.parkingmanagementsystem.parking.ticket;


import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ParkingTicket")
@Log4j2
public class ParkingTicketController {
    ParkingTicketService parkingTicketService;

    public ParkingTicketController(ParkingTicketService parkingTicketService) {
        this.parkingTicketService = parkingTicketService;
    }

    @GetMapping("/{tokenNumber}")
    public ParkingTicket getParkingTicket(@PathVariable("tokenNumber") String tokenNumber) {
        log.info("Received tokenNumber = {}", tokenNumber);
        return parkingTicketService.getParkingTicket(tokenNumber);
    }

    @GetMapping()
    public List<ParkingTicket> getAllParkingTicket() {
        return parkingTicketService.getAllParkingTicket();
    }

    @PostMapping
    public String createParkingTicket(@RequestBody ParkingTicket parkingTicket) {
        log.info("Received ParkingTicket details = {}", parkingTicket);
        parkingTicketService.createParkingTicket(parkingTicket);
        return "ParkingTicket created successfully";
    }

    @PutMapping
    public String updateParkingTicket(@RequestBody ParkingTicket parkingTicket) {
        parkingTicketService.updateParkingTicket(parkingTicket);
        return "ParkingTicket updated successfully";
    }

    @DeleteMapping("{tokenNumber}")
    public String deleteParkingTicket(@PathVariable("tokenNumber") String tokenNumber) {
        parkingTicketService.deleteParkingTicket(tokenNumber);

        return "ParkingTicket deleted successfully";
    }
}
