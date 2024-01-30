package com.example.parkingmanagementsystem.parking.floor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "parking_floor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFloor {
    @Id
    private String floorId;

    // foreign key
    private String lotId;


    @Enumerated(EnumType.STRING)
    private FloorStatus status;

//    @OneToMany(mappedBy = "ParkingFloor", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ParkingSpot> parkingSpot;
}
