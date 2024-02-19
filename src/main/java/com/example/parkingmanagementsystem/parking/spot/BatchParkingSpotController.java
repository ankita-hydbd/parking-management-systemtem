package com.example.parkingmanagementsystem.parking.spot;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Create")
@Log4j2
public class BatchParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    public BatchParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }


    @PostMapping
    BatchCreateParkingSpotResponse batchCreateParkingSpot(@RequestBody BatchCreateParkingSpotRequest batchRequest)
    {
        log.info("Received  batch spot create request details = {}", batchRequest);

        List<ParkingSpot> createdParkingSpotList =
                parkingSpotService.createParkingSpotBatch(batchRequest.getParkingSpotList());

        final BatchCreateParkingSpotResponse batchCreateParkingSpotResponse = new BatchCreateParkingSpotResponse();
        batchCreateParkingSpotResponse.setCreatedParkingSpotList(createdParkingSpotList);

        log.info("Final batch create response = {}", batchCreateParkingSpotResponse);
        return batchCreateParkingSpotResponse;
    }


}