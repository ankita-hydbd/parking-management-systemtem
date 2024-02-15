package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkTicketBookResponse {
   List<ParkingTicket> parkingTicketList;
   BookedSummary bookedSummary;
   private String tokenNumber;
}