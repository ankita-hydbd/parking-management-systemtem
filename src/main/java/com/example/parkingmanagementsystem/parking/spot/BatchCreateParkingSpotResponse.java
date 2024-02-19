package com.example.parkingmanagementsystem.parking.spot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchCreateParkingSpotResponse {

    List<ParkingSpot> createdParkingSpotList;
}
