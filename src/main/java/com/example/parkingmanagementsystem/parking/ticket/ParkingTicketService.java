package com.example.parkingmanagementsystem.parking.ticket;

import java.util.List;

public interface ParkingTicketService {
    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket);
    public ParkingTicket updateParkingTicket(ParkingTicket parkingTicket);
    public String deleteParkingTicket(String tokenNumber);
    public ParkingTicket getParkingTicket(String tokenNumber);
    public List<ParkingTicket> getAllParkingTicket();

}
