package com.example.parkingmanagementsystem.parking.ticket;


import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.ticket.book.BulkTicketBookResponse;
import com.example.parkingmanagementsystem.parking.ticket.exception.ParkingTicketNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@Log4j2
public class ParkingTicketServiceImpl implements ParkingTicketService {
    private ParkingTicketRepository parkingTicketRepository;
    private ParkingManagementProperties config;

    public ParkingTicketServiceImpl(ParkingTicketRepository parkingTicketRepository,
                                    ParkingManagementProperties config) {
        this.parkingTicketRepository = parkingTicketRepository;
        this.config = config;
    }


    @Override
    public ParkingTicket createParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public List<ParkingTicket> createParkingTicketBatch(List<ParkingTicket> parkingTicketList) {
        return parkingTicketRepository.saveAll(parkingTicketList);
    }

    @Override
    public ParkingTicket updateParkingTicket(ParkingTicket parkingTicket) {
        return parkingTicketRepository.save(parkingTicket);
    }

    @Override
    public String deleteParkingTicket(String tokenNumber) {
        parkingTicketRepository.deleteById(tokenNumber);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingTicket getParkingTicket(String tokenNumber) {
        if (parkingTicketRepository.findById(tokenNumber).isEmpty())
            throw new ParkingTicketNotFoundException("ParkingTicket detail doesn't exist");

        return parkingTicketRepository.findById(tokenNumber).get();
    }

    @Override
    public List<ParkingTicket> getAllParkingTicket() {
        log.info("Config values = {}", config);
        return parkingTicketRepository.findAll();
    }

    @Override
    public List<ParkingTicket> getAllParkingTicketByIds(List<String> tokenNumberList) {
        if (parkingTicketRepository.findAllById(tokenNumberList).isEmpty())
            throw new ParkingTicketNotFoundException("ParkingTicket detail doesn't exist");
        List<ParkingTicket> ticketList = parkingTicketRepository.findAllById(tokenNumberList);
        return ticketList;
    }

    public List<ParkingTicket> saveAll(List<ParkingTicket> parkingTicketList) {
        return parkingTicketRepository.saveAll(parkingTicketList);
    }

}
