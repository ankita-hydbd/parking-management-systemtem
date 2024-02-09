package com.example.parkingmanagementsystem.parking.ticket;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Table(name = "parking_ticket")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tokenNumber;
    private String vehicleNumber;
    private String spotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double ticketAmount;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
