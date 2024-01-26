package com.example.parkingmanagementsystem.attendantPortal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "attendant_info")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ParkingAttendant {

    @Id
    private String attendantId;
}
