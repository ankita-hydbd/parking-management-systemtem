package com.example.parkingmanagementsystem.parking.ticket.book.model;

import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CloseTicketRequest {

    private Double ticketAmount;
    private TicketStatus ticketStatus;
}
