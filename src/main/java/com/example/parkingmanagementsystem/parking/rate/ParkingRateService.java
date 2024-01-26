package com.example.parkingmanagementsystem.parking.rate;

import java.util.List;

public interface ParkingRateService {
    public String createParkingRate(ParkingRate parkingRate);
    public String updateParkingRate(ParkingRate parkingRate);
    public String deleteParkingRate(String hourlyRate);
    public ParkingRate getParkingRate(String hourlyRate);
    public List<ParkingRate> getAllParkingRate();

}
