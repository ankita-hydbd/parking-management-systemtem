package com.example.parkingmanagementsystem.parking.floor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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
    public ParkingFloor getParkingSpot(@PathVariable("FloorId") String floorId) {
        log.info("Received floorId = {}",floorId);
        return parkingFloorService.getParkingFloor(floorId);
    }

    @GetMapping()
    public List<ParkingFloor> getAllParkingFloor()
    {
        return parkingFloorService.getAllParkingFloor();
    }

    @PostMapping
    public String createParkingFloor(@RequestBody ParkingFloor parkingFloor) {
        log.info("Received lotId details = {}",parkingFloor);
        parkingFloorService.createParkingFloor(parkingFloor);
        return "Parking Floor created successfully";
    }

    @PutMapping
    public String updateParkingFloor(@RequestBody ParkingFloor parkingFloor ) {
        parkingFloorService.updateParkingFloor(parkingFloor);
        return "Parking floor updated successfully";
    }

    @DeleteMapping("{floorId}")
    public String deleteParkingFloor(@PathVariable("floorId") String floorId) {
      parkingFloorService.deleteParkingFloor(floorId);

        return "Parking floor deleted successfully";
    }
}
