package com.example.parkingmanagementsystem.parking.floor;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor,String> {
}
