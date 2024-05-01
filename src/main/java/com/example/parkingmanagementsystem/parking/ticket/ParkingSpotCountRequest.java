package com.example.parkingmanagementsystem.parking.ticket;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkingSpotCountRequest {
    private ParkingSpotType parkingSpotType;
    private Integer count;
}
