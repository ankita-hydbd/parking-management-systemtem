package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.spot.BatchCreateParkingSpotRequest;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicketService;
import com.example.parkingmanagementsystem.parking.ticket.book.model.CalculateFareBatchBookTicketRequest;
import com.example.parkingmanagementsystem.parking.ticket.book.model.CalculateFareBatchBookTicketResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/BatchTicketFare")
@Log4j2
public class BatchCalculateFareController {
    private ParkingSpotService parkingSpotService;
    private ParkingTicketService parkingTicketService;
    private ParkingManagementProperties config;

    public BatchCalculateFareController(ParkingSpotService parkingSpotService, ParkingTicketService parkingTicketService, ParkingManagementProperties config) {
        this.parkingSpotService = parkingSpotService;
        this.parkingTicketService = parkingTicketService;
        this.config = config;
    }

    @PostMapping
    public CalculateFareBatchBookTicketResponse
    calculateBatchFare(@RequestBody CalculateFareBatchBookTicketRequest calculateFareBatchBookTicketRequest) {
        final LocalDateTime endTime = LocalDateTime.now();
        log.info("Received token number = {}", calculateFareBatchBookTicketRequest);
        List<ParkingTicket> bookedTicketList = parkingTicketService.getAllParkingTicketByIds
                (calculateFareBatchBookTicketRequest.getTokenNumberList());

        // Fetch the spot type from ParkingSpot table.
//        Map<String, ParkingSpot> bookedParkingSpotMap = new HashMap<>();
        // TODO replace below single record fetch by findAllById like we have done for ParkingTicket.
//        for (ParkingTicket ticketList : bookedTicketList) {
//            final ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(ticketList.getSpotId());
//            log.info("Booked Spot Details = {}", parkingSpot);
//            bookedParkingSpotMap.put(parkingSpot.getSpotId(), parkingSpot);
//        }

        List<String> spotIds = bookedTicketList.stream().map(s -> s.getSpotId()).collect(Collectors.toList());
        List<ParkingSpot> bookedParkingSpotLists = parkingSpotService.getAllParkingSpotByIds(spotIds);

        // Practice stream for converting list of custom objec to map of id, custom object.
        Map<String, ParkingSpot> bookedParkingSpotMap = bookedParkingSpotLists.stream()
                .collect(Collectors.toMap(ParkingSpot::getSpotId, Function.identity()));

        // Get hourly Rate from config
        final Map<String, Integer> hourlyRateMap = config.getHourlyRate();
        log.info("Hourly Rate Map = {}, Config = {}", hourlyRateMap, config);

        Double totalParkingFare = 0.0;
        for (ParkingTicket ticket : bookedTicketList) {
            long parkingDurationMins = ticket.getStartTime().until(endTime, ChronoUnit.MINUTES);
            // next step : find vehicle type? it is ParkingSpotType
            final ParkingSpot relatedParkingSpotDetails = bookedParkingSpotMap.get(ticket.getSpotId());
            final ParkingSpotType parkingSpotType = relatedParkingSpotDetails.getParkingSpotType();

            // next step : find hourly rate of that vehicle type
            final Integer spotTypeHourlyRate = hourlyRateMap.get(parkingSpotType.name());

            // next step : multiple the hourly rate by total duration
            Double parkingFare = calculateTotalFare(parkingDurationMins, spotTypeHourlyRate.doubleValue());
            ticket.setTicketAmount(parkingFare);
            totalParkingFare += parkingFare;
            log.info("Ticket with updated fare = {}", ticket);
        }
        CalculateFareBatchBookTicketResponse response=new CalculateFareBatchBookTicketResponse();
        response.setParkingTicketList(bookedTicketList);
        log.info("List of Parking Ticket={}",bookedTicketList);
        response.setTicketAmount(totalParkingFare);
        log.info("Total parking fare = {} " ,totalParkingFare);
        return  response;

    }

    private Double calculateTotalFare(long parkingDurationMins, Double parkingFare) {
        if (parkingDurationMins > 60) {
            long deltaDuration = parkingDurationMins - 60;
            parkingFare = parkingFare + (Math.ceil(deltaDuration / 60.0) * parkingDurationMins);
        }
        return parkingFare;
    }
}
