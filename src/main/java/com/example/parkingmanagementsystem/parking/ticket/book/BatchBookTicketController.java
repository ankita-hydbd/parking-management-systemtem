package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.spot.BatchCreateParkingSpotRequest;
import com.example.parkingmanagementsystem.parking.spot.BatchCreateParkingSpotResponse;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicketService;
import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import com.example.parkingmanagementsystem.parking.ticket.book.model.BatchCloseTicketRequest;
import com.example.parkingmanagementsystem.parking.ticket.book.model.BatchCloseTicketResponse;
import com.example.parkingmanagementsystem.parking.ticket.book.model.CloseTicketRequest;
import com.example.parkingmanagementsystem.parking.ticket.book.model.TicketClosureDetail;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

        List<ParkingTicket> bookedParkingTicketList = parkingTicketService.createParkingTicketBatch(stagedParkingTicketList);

        log.info("Booked Parking Tickets = {}", bookedParkingTicketList);


        BulkTicketBookResponse response = new BulkTicketBookResponse();
        response.setParkingTicketList(bookedParkingTicketList);

        BookedSummary summary = new BookedSummary();
        summary.setBookedIntegerCount(updatedSpotCount);
        response.setBookedSummary(summary);
        log.info("bulk response summary= {}", response);

        return response;
    }

    @PostMapping("/CloseBatchTicket")
    BatchCloseTicketResponse closeTicket(@RequestBody BatchCloseTicketRequest batchCloseTicketRequest) {
        log.info("Received tokenNumberList = {}, request body = {}", batchCloseTicketRequest);
        List<TicketClosureDetail> ticketClosureDetails = batchCloseTicketRequest.getTicketClosureDetails();
        List<String> tokenNumberList = batchCloseTicketRequest.getTicketClosureDetails().stream()
                .map(l -> l.getTokenNumber()).collect(Collectors.toList());
        // fetch ticket details
        final List<ParkingTicket> bookedTicketList = parkingTicketService.getAllParkingTicketByIds(tokenNumberList);
        log.info("List of Booked Ticket details = {}", bookedTicketList);

        List<String> spotIdList = new ArrayList<>();

        for (int i = 0; i < bookedTicketList.size(); i++) {
            TicketClosureDetail relatedTicketClosureDetail = null;

            for (int j = 0; i < ticketClosureDetails.size(); j++) {
                if (bookedTicketList.get(i).getTokenNumber().equals(ticketClosureDetails.get(j).getTokenNumber())) {
                    relatedTicketClosureDetail = ticketClosureDetails.get(j);
                    break;
                }
            }

            bookedTicketList.get(i).setTicketAmount(relatedTicketClosureDetail.getTicketAmount());
            bookedTicketList.get(i).setTicketStatus(relatedTicketClosureDetail.getTicketStatus());
            spotIdList.add(bookedTicketList.get(i).getSpotId());
        }
        log.info("spotId list received ={}", spotIdList);
        for (int i = 0; i < spotIdList.size(); i++) {
            parkingSpotService.updateParkingSpotStatus(spotIdList.get(i), true);

        }
        parkingTicketService.saveAll(parkingTicketService.getAllParkingTicket());


        BatchCloseTicketResponse response = new BatchCloseTicketResponse();
        response.setParkingTicketList(bookedTicketList);
        log.info("booked ticket list={}", bookedTicketList);
        return response;
    }


}
