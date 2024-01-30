package com.example.parkingmanagementsystem.parking.spot.model;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingRequest {

    private String lotId;
    private ParkingSpotType parkingSpotType;
}
