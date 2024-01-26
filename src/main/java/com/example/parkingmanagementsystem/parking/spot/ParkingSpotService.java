package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.lot.ParkingLot;
import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;

import java.util.List;

public interface ParkingSpotService {
    public String createParkingSpot(ParkingSpot parkingSpot);
    public String updateParkingSpot(ParkingSpot parkingSpot);
    public String deleteParkingSpot(String spotId);
    public ParkingSpot getParkingSpot(String spotId);
    public List<ParkingSpot> getAllParkingSpot();

    public ParkingSpot findAvailableSpotByLotAndType(ParkingRequest parkingRequest);

    public Integer updateParkingSpotStatus(String spotId, Boolean isFree);
}
