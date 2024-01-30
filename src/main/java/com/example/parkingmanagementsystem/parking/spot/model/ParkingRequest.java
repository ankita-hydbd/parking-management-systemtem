package com.example.parkingmanagementsystem.parking.spot.model;

import com.example.parkingmanagementsystem.parking.floor.FloorStatus;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingRequest {
//    private String floorIdList;
//    private String lotId;
    private FloorStatus status;
    private Boolean isFree;
//    private String spotId;
    private ParkingSpotType parkingSpotType;

}
