package com.example.parkingmanagementsystem.parking.lot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_lot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    @Id
    private String lotId;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
