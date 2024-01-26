package com.example.parkingmanagementsystem.parking.lot;

import java.util.List;

public interface ParkingLotService {
    public String createParkingLot(ParkingLot parkingLot);
    public String updateParkingLot(ParkingLot parkingLot);
    public String deleteParkingLot(String lotId);
    public ParkingLot getParkingLot(String lotId);
    public List<ParkingLot> getAllParkingLot();

}
