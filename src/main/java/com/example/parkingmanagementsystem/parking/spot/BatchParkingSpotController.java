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
    List<ParkingSpot>createParkingSpotBatch(@RequestBody List<ParkingSpot> parkingSpotList)
    {
        log.info("Received  batch spotId details = {}", parkingSpotList);
        return parkingSpotService.createParkingSpotBatch(parkingSpotList);

    }


}