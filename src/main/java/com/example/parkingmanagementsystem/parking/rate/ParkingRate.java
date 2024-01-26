package com.example.parkingmanagementsystem.parking.rate;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ParkingRate {
    @Id
    @Enumerated(EnumType.STRING)
    private ParkingSpotType parkingSpotType;
    private Integer hourlyRate;


}
