package com.example.parkingmanagementsystem.parking.floor.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorAvailabilityCheck {
    private String floorId;
    private String lotId;
    @Enumerated(EnumType.STRING)
    private FloorAvailabilityStatus floorAvailabilityStatus;

}
