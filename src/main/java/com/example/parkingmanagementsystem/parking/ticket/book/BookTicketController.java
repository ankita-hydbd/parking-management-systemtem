package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicketService;
import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import com.example.parkingmanagementsystem.parking.ticket.book.model.BookTicketRequest;
import com.example.parkingmanagementsystem.parking.ticket.book.model.CloseTicketRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/BookTicket")
@Log4j2
public class BookTicketController {

    private final ParkingTicketService parkingTicketService;
    private final ParkingSpotService parkingSpotService;
    private final ParkingManagementProperties config;

    public BookTicketController(ParkingTicketService parkingTicketService,
                                ParkingSpotService parkingSpotService,
                                ParkingManagementProperties config) {
        this.parkingTicketService = parkingTicketService;
        this.parkingSpotService = parkingSpotService;
        this.config = config;
    }

    @PostMapping
    ParkingTicket bookTicket(@RequestBody final BookTicketRequest bookTicketRequest) {
        log.info("Received Book Ticket Request = {}", bookTicketRequest);
        // Mark the spotId as not available.
        final Integer spotRecordUpdateCount =
                parkingSpotService.updateParkingSpotStatus(bookTicketRequest.getSpotId(), false);
        log.info("Updated ParkingSpot records count = {}", spotRecordUpdateCount);

        // Preparing a ParkingTicket object using bookTicketRequest
        ParkingTicket stagedTicket = new ParkingTicket();
        stagedTicket.setSpotId(bookTicketRequest.getSpotId());
        stagedTicket.setVehicleNumber(bookTicketRequest.getVehicleNumber());
        stagedTicket.setStartTime(LocalDateTime.now());
        stagedTicket.setTicketStatus(TicketStatus.ACTIVE);
        log.info("Staged parking ticket = {}", stagedTicket);

        // save this ticket in DB using ParkingTicketService.
        final ParkingTicket bookedTicket = parkingTicketService.createParkingTicket(stagedTicket);
        log.info("Booked parking ticket = {}", bookedTicket);
        return bookedTicket;
    }

    @GetMapping("{tokenNumber}/Fare")
    ParkingTicket calculateFare(@PathVariable("tokenNumber") final String tokenNumber) {
        log.info("Received token number = {}", tokenNumber);
        final ParkingTicket bookedTicket = parkingTicketService.getParkingTicket(tokenNumber);

        log.info("Parking Ticket Details = {}", bookedTicket);

        final LocalDateTime endTime = LocalDateTime.now();
        final long parkingDurationMins = bookedTicket.getStartTime().until(endTime, ChronoUnit.MINUTES);

        // Fetch the spot type from ParkingSpot table.
        final ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(bookedTicket.getSpotId());
        log.info("Booked Spot Details = {}", parkingSpot);

        // Get hourly Rate from config
        final Map<String, Integer> hourlyRateMap = config.getHourlyRate();
        final Integer spotTypeHourlyRate = hourlyRateMap.get(parkingSpot.getParkingSpotType().name());
        log.info("Spot Hourly Rate = {}, Config = {}", spotTypeHourlyRate, config);

        // apply min ticket fare
        Double parkingFare = (double) spotTypeHourlyRate;

        // compute over charges
        if (parkingDurationMins > 60) {
            long deltaDuration = parkingDurationMins - 60;
            parkingFare = parkingFare + (Math.ceil(deltaDuration / 60.0) * spotTypeHourlyRate);
        }
        final ParkingTicket ticketWithUpdatedFare = bookedTicket.toBuilder()
                .ticketAmount(parkingFare)
                .build();
        log.info("Ticket with updated fare = {}", ticketWithUpdatedFare);
        return ticketWithUpdatedFare;
    }

    @PostMapping("{tokenNumber}/Close")
    ParkingTicket closeTicket(@PathVariable("tokenNumber") final String tokenNumber,
                              @RequestBody CloseTicketRequest closeTicketRequest) {
        log.info("Received tokenNumber = {}, request body = {}", tokenNumber, closeTicketRequest);
        // fetch ticket details
        final ParkingTicket bookedTicket = parkingTicketService.getParkingTicket(tokenNumber);
        log.info("Booked Ticket details = {}", bookedTicket);

        // prepare updated records
        ParkingTicket ticketUpdatedWithFareAndStatus = bookedTicket.toBuilder()
                .ticketAmount(closeTicketRequest.getTicketAmount())
                .ticketStatus(closeTicketRequest.getTicketStatus()).build();
      return ticketUpdatedWithFareAndStatus;
        // save and return final ticket record


    }
}
