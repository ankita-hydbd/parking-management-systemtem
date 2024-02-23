package com.example.parkingmanagementsystem.parking.ticket.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchCloseTicketRequest {
    List<TicketClosureDetail> ticketClosureDetails;
}
