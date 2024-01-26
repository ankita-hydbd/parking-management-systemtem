package com.example.parkingmanagementsystem.attendantPortal;


import com.example.parkingmanagementsystem.attendantPortal.exception.ParkingAttendantNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ParkingAttendantServiceImpl implements ParkingAttendantService {
    private ParkingAttendantRepository parkingAttendantRepository;

    public ParkingAttendantServiceImpl(ParkingAttendantRepository parkingAttendantRepository) {
        this.parkingAttendantRepository = parkingAttendantRepository;
    }


    @Override
    public String createParkingAttendant(ParkingAttendant parkingAttendant) {
       parkingAttendantRepository.save(parkingAttendant);
        return "successfully created";
    }

    @Override
    public String updateParkingAttendant(ParkingAttendant parkingAttendant) {
        parkingAttendantRepository.save(parkingAttendant);
        return "successfully updated";
    }

    @Override
    public String deleteParkingAttendant(String attendantId) {
        parkingAttendantRepository.deleteById(attendantId);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingAttendant getParkingAttendant(String attendantId)  {
        if(parkingAttendantRepository.findById(attendantId).isEmpty())
            throw new ParkingAttendantNotFoundException("ParkingAttendant detail doesn't exist");

        return  parkingAttendantRepository.findById(attendantId).get();
    }

    @Override
    public List<ParkingAttendant> getAllParkingAttendant() {
        return parkingAttendantRepository.findAll();
    }
}
