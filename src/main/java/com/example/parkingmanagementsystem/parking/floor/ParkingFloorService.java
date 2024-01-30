package com.example.parkingmanagementsystem.parking.floor;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;

import java.util.List;

public interface ParkingFloorService {
    public String createParkingFloor(ParkingFloor parkingFloor);
    public String updateParkingFloor(ParkingFloor parkingFloor);
    public String deleteParkingFloor(String floorId);
    public ParkingFloor getParkingFloor(String floorId);
    public List<ParkingFloor> getAllParkingFloor();

}
