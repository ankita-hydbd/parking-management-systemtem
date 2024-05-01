package com.example.parkingmanagementsystem.parking.spot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "parking_spot")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpot {

    @Id
    private String spotId;
    private String lotId;
    private String floorId;

    @Enumerated(EnumType.STRING)
    private ParkingSpotType parkingSpotType;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean isFree;
}