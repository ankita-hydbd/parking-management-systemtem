package com.example.parkingmanagementsystem.parking.rate;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ParkingRate")
@Log4j2

public class ParkingRateController {
    ParkingRateService parkingRateService;

    public ParkingRateController(ParkingRateService parkingRateService) {
        this.parkingRateService = parkingRateService;
    }

    @GetMapping("/{hourlyRate}")
    public ParkingRate getParkingRate(@PathVariable("hourlyRate") String hourlyRate) {
        log.info("Received hourlyRate = {}",hourlyRate);
        return parkingRateService.getParkingRate(hourlyRate);
    }

    @GetMapping()
    public List<ParkingRate> getAllParkingRate() {
        return parkingRateService.getAllParkingRate();
    }

    @PostMapping
    public String createParkingRate(@RequestBody ParkingRate parkingRate) {
        log.info("Received ParkingRate details = {}", parkingRate);
       parkingRateService.createParkingRate(parkingRate);
        return "ParkingRate created successfully";
    }

    @PutMapping
    public String updateParkingRate(@RequestBody ParkingRate parkingRate) {
        parkingRateService.updateParkingRate(parkingRate);
        return "Parking Rate updated successfully";
    }

    @DeleteMapping("{hourlyRate}")
    public String deleteParkingRate(@PathVariable("hourlyRate") String hourlyRate) {
        parkingRateService.deleteParkingRate(hourlyRate);

        return "Parking Rate deleted successfully";
    }
}
