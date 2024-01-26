package com.example.parkingmanagementsystem.parking.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket,String> {
}
