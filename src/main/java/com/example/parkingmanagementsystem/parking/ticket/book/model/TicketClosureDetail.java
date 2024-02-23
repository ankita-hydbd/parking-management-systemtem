package com.example.parkingmanagementsystem.parking.ticket.book.model;

import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketClosureDetail {
    String tokenNumber;
    Double ticketAmount;
    TicketStatus ticketStatus;
}
