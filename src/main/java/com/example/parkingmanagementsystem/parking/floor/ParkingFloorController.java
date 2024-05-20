package com.example.parkingmanagementsystem.parking.floor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("ParkingFloor")
public class ParkingFloorController {

    ParkingFloorService parkingFloorService;

    public ParkingFloorController(ParkingFloorService parkingFloorService) {
        this.parkingFloorService = parkingFloorService;
    }

    @GetMapping("/{FloorId}")
    public ParkingFloor getParkingFloor(@PathVariable("FloorId") String floorId) {
        log.info("Received floorIdList = {}", floorId);
        return parkingFloorService.getParkingFloor(floorId);
    }

    @GetMapping()
    public List<ParkingFloor> getAllParkingFloor() {
        return parkingFloorService.getAllParkingFloor();
    }

    @PostMapping
    public String createParkingFloor(@RequestBody ParkingFloor parkingFloor) {
        log.info("Received lotId details = {}", parkingFloor);
        parkingFloorService.createParkingFloor(parkingFloor);
        return "Parking Floor created successfully";
    }

    @PutMapping
    public String updateParkingFloor(@RequestBody ParkingFloor parkingFloor) {
        parkingFloorService.updateParkingFloor(parkingFloor);
        return "Parking floor updated successfully";
    }

    @DeleteMapping("{floorIdList}")
    public String deleteParkingFloor(@PathVariable("floorIdList") String floorId) {
        parkingFloorService.deleteParkingFloor(floorId);

        return "Parking floor deleted successfully";
    }

    @PutMapping("{floorId}")
    public String updateParkingFloorStatus(@PathVariable("floorId") String floorId,
                                           @RequestBody ParkingFloorUpdateRequest updateRequest) {
        log.info("Received parking floor update for floorId = {}, updateRequest={}",
                floorId, updateRequest);
        parkingFloorService.updateParkingFloorStatus(floorId, updateRequest.getStatus());
        return "ParkingFloor availability status updated successfully";
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateMultipleParkingFloorStatus(
            @RequestBody ParkingFloorUpdateRequest updateRequest)
    {
        log.info("Received parking floor update for Multiple  updateRequest={}", updateRequest);
        Integer updatedCount = parkingFloorService.updateMultipleParkingFloorStatus(updateRequest.getFloorIdList(),updateRequest.getStatus());
        return ResponseEntity.ok("Updated " + updatedCount + " records");
    }
}
