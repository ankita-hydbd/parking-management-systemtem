package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.spot.BatchParkingSpotRequest;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicketService;
import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import jakarta.websocket.server.PathParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.ObjectInputFilter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/BatchBookTicket")
@Log4j2
public class BatchBookTicketController {

    private final ParkingSpotService parkingSpotService;
    private final ParkingTicketService parkingTicketService;
    private final ParkingManagementProperties config;

    public BatchBookTicketController(ParkingSpotService parkingSpotService, ParkingTicketService parkingTicketService, ParkingManagementProperties config) {
        this.parkingSpotService = parkingSpotService;
        this.parkingTicketService = parkingTicketService;
        this.config = config;
    }

    @PostMapping()
    public BulkTicketBookResponse bookBulkParkingSpots(@RequestBody ParkingSpotCountRequest parkingSpotCountRequest) {
        log.info("ParkingSpotCountRequest={}", parkingSpotCountRequest);
        List<ParkingSpot> parkingSpots = parkingSpotService.getAvailableSpotType(parkingSpotCountRequest);
        log.info("Available Spots = {}", parkingSpots);
        List<String> freeSpotIds = parkingSpots.stream().map(ps -> ps.getSpotId()).collect(Collectors.toList());
        final int updatedSpotCount = parkingSpotService.updateBatchBookSpotAvailability(false, true, freeSpotIds);
        log.info("Updated spot count = {}", updatedSpotCount);

        List<ParkingTicket> stagedParkingTicketList = parkingSpots.stream()
                .map(parkingSpot -> ParkingTicket.builder()
                        .spotId(parkingSpot.getSpotId())
                        .ticketStatus(TicketStatus.ACTIVE)
                        .startTime(LocalDateTime.now())
                        .vehicleNumber("BULK_BOOKING")
                        .build())
                .collect(Collectors.toList());

        List<ParkingTicket> bookedParkingTicketList = parkingTicketService.createParkingTicketBatch(stagedParkingTicketList);;
        log.info("Booked Parking Tickets = {}", bookedParkingTicketList);


        BulkTicketBookResponse response=new BulkTicketBookResponse();
        response.setParkingTicketList(bookedParkingTicketList);

        BookedSummary summary=new BookedSummary();
        summary.setBookedIntegerCount(updatedSpotCount);
        response.setBookedSummary(summary);
        log.info("bulk response summary= {}",response);

        return response;
    }




}
