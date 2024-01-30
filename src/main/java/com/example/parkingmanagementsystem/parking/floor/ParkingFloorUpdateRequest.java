package com.example.parkingmanagementsystem.parking.floor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingFloorUpdateRequest {

    private List<String> floorIdList;
    private FloorStatus status;
}
/*
{
    "floorIdList":["1", "2"],
    "status" : "AVAILABLE"
}


 */