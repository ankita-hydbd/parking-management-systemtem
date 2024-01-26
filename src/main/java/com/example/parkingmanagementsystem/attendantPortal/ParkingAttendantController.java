package com.example.parkingmanagementsystem.attendantPortal;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ParkingAttendant")
@Log4j2

public class ParkingAttendantController {
    ParkingAttendantService parkingAttendantService;

    public ParkingAttendantController(ParkingAttendantService parkingAttendantService) {
        this.parkingAttendantService = parkingAttendantService;
    }

    @GetMapping("/{attendantId}")
    public ParkingAttendant getParkingAttendant(@PathVariable("attendantId") String attendantId) {
        log.info("Received attendantId = {}",attendantId);
        return parkingAttendantService.getParkingAttendant(attendantId);
    }

    @GetMapping()
    public List<ParkingAttendant> getAllParkingAttendant() {
        return parkingAttendantService.getAllParkingAttendant();
    }

    @PostMapping
    public String createParkingAttendant(@RequestBody ParkingAttendant parkingAttendant) {
        log.info("Received ParkingAttendant details = {}", parkingAttendant);
      parkingAttendantService.createParkingAttendant(parkingAttendant);
        return "ParkingAttendant created successfully";
    }

    @PutMapping
    public String updateParkingAttendant(@RequestBody ParkingAttendant parkingAttendant) {
        parkingAttendantService.updateParkingAttendant(parkingAttendant);
        return "ParkingAttendant updated successfully";
    }

    @DeleteMapping("{attendantId}")
    public String deleteParkingAttendant(@PathVariable("attendantId") String attendantId) {
       parkingAttendantService.deleteParkingAttendant(attendantId);

        return "ParkingAttendant deleted successfully";
    }
}
