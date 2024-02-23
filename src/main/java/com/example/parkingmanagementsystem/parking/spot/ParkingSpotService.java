package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;

import java.util.List;

public interface ParkingSpotService {

    public String createParkingSpot(ParkingSpot parkingSpot);

    public String updateParkingSpot(ParkingSpot parkingSpot);

    public String deleteParkingSpot(String spotId);

    public ParkingSpot getParkingSpot(String spotId);

    public List<ParkingSpot> getAllParkingSpot();

    public List<ParkingSpot> findAvailableFloorIdSpotIdBySpotType(ParkingRequest parkingRequest);

    public Integer updateParkingSpotStatus(String spotId, Boolean isFree);

    List<Object[]> CountAvailableSpotType();

    List<ParkingSpot> getAvailableSpotType(ParkingSpotCountRequest parkingSpotCountRequest);

    Integer updateBatchBookSpotAvailability(Boolean isFreeUpdated, Boolean isFreeExisting, List<String> spotIdList);

    List<ParkingSpot> createParkingSpotBatch(List<ParkingSpot> parkingSpotList);

    List<ParkingSpot> getAllParkingSpotByIds(List<String> spotIds);
}
