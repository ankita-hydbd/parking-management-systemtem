package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ParkingTicket/Bulk")
@Log4j2
public class BulkBookTicketController {

    private final ParkingSpotService parkingSpotService;

    public BulkBookTicketController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping("/book")
    public List<ParkingSpot> getAvailableSpotType(@RequestBody ParkingSpotCountRequest parkingSpotCountRequest) {
        log.info("Available spot type={}", parkingSpotCountRequest);
        return parkingSpotService.getAvailableSpotType(parkingSpotCountRequest);
    }
}
