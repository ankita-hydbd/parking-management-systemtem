package com.example.parkingmanagementsystem.parking.ticket.book.model;

import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketClosureDetail {
    String tokenNumber;
    Double ticketAmount;
    TicketStatus ticketStatus;
}
