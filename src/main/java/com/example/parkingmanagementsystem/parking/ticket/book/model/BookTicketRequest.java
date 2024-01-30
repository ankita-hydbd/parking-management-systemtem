package com.example.parkingmanagementsystem.parking.ticket.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookTicketRequest {

    private String spotId;

    private String vehicleNumber;
}
