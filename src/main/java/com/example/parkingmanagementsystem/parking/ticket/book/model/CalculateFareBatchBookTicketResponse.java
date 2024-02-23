package com.example.parkingmanagementsystem.parking.ticket.book.model;

import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateFareBatchBookTicketResponse {
  List<ParkingTicket> parkingTicketList;
  Double ticketAmount;

}
