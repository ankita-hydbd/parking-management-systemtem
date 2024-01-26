package com.example.parkingmanagementsystem.parking.floor.model;

import com.example.parkingmanagementsystem.parking.floor.ParkingFloor;
import com.example.parkingmanagementsystem.parking.floor.ParkingFloorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class FloorAvailabilityController {

    private ParkingFloorService parkingFloorService;

    @GetMapping("/{FloorAvailable}")
    public ParkingFloor getParkingSpot(@PathVariable("FloorId") String floorId) {

        return parkingFloorService.getParkingFloor(floorId);
    }

}
