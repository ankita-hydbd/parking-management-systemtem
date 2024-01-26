package com.example.parkingmanagementsystem.parking.lot;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ParkingLot")
@Log4j2
public class ParkingLotController {
    ParkingLotService parkingLotService;

    public ParkingLotController( ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping("/{lotId}")
    public ParkingLot getParkingLot(@PathVariable("lotId") String lotId) {
        log.info("Received lotId = {}",lotId);
        return parkingLotService.getParkingLot(lotId);
    }

    @GetMapping()
    public List<ParkingLot> getAllParkingLot() {
        return parkingLotService.getAllParkingLot();
    }

    @PostMapping
    public String createParkingLot(@RequestBody ParkingLot parkingLot) {
        log.info("Received lotId details = {}",parkingLot);
        parkingLotService.createParkingLot(parkingLot);
        return "Parking lot created successfully";
    }

    @PutMapping
    public String updateParkingLot(@RequestBody ParkingLot parkingLot ) {
      parkingLotService.updateParkingLot(parkingLot);
        return "Parking lot updated successfully";
    }

    @DeleteMapping("{lotId}")
    public String deleteParkingLot(@PathVariable("lotId") String lotId) {
       parkingLotService.deleteParkingLot(lotId);

        return "Parking lot deleted successfully";
    }
}
