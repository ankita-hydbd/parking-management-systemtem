package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ParkingSpot")
@Log4j2
public class ParkingSpotController {

    ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping("/{spotId}")
    public ParkingSpot getParkingSpot(@PathVariable("SpotId") String spotId) {
        log.info("Received spotId = {}", spotId);
        return parkingSpotService.getParkingSpot(spotId);
    }

    @GetMapping()
    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotService.getAllParkingSpot();
    }

    @PostMapping
    public String createParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        log.info("Received lotId details = {}", parkingSpot);
        parkingSpotService.createParkingSpot(parkingSpot);
        return "Parking spot created successfully";
    }

    @PutMapping
    public String updateParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        parkingSpotService.updateParkingSpot(parkingSpot);
        return "Parking spot updated successfully";
    }

    @DeleteMapping("{spotId}")
    public String deleteParkingSpot(@PathVariable("spotId") String spotId) {
        parkingSpotService.deleteParkingSpot(spotId);

        return "Parking spot deleted successfully";
    }

    @GetMapping("/FindAvailableSpot")
    public ParkingSpot findAvailableSpot(@RequestBody final ParkingRequest parkingRequest) {
        log.info("Received parking request = {}", parkingRequest);
        return parkingSpotService.findAvailableSpotByLotAndType(parkingRequest);
    }
}
