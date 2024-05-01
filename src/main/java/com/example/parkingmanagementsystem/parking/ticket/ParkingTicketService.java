package com.example.parkingmanagementsystem.parking.ticket;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.ticket.book.BulkTicketBookResponse;
import lombok.SneakyThrows;

import java.util.List;

public interface ParkingTicketService {

    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket);

    public List<ParkingTicket> createParkingTicketBatch(List<ParkingTicket> parkingTicketList);
    public ParkingTicket updateParkingTicket(ParkingTicket parkingTicket);
    public String deleteParkingTicket(String tokenNumber);
    public ParkingTicket getParkingTicket(String tokenNumber);
    public List<ParkingTicket> getAllParkingTicket();


    public List<ParkingTicket> getAllParkingTicketByIds(List<String> tokenNumberList);

    public List<ParkingTicket> saveAll(List<ParkingTicket> parkingTicketList);


}
