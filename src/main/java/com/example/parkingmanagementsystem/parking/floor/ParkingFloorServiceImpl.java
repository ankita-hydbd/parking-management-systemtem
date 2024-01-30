package com.example.parkingmanagementsystem.parking.floor;

import com.example.parkingmanagementsystem.parking.floor.exception.ParkingFloorNotFoundException;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class ParkingFloorServiceImpl implements ParkingFloorService {
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingFloorServiceImpl(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }


    @Override
    public String createParkingFloor(ParkingFloor parkingFloor) {
        parkingFloorRepository.save(parkingFloor);
        return "successfully created";
    }

    @Override
    public String updateParkingFloor(ParkingFloor parkingFloor) {
        parkingFloorRepository.save(parkingFloor);
        return "successfully updated";
    }

    @Override
    public String deleteParkingFloor(String floorId) {
        parkingFloorRepository.deleteById(floorId);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingFloor getParkingFloor(String floorId) {
        if (parkingFloorRepository.findById(floorId).isEmpty())
            throw new ParkingFloorNotFoundException("ParkingFloor detail doesn't exist");

        return parkingFloorRepository.findById(floorId).get();
    }

    @Override
    public List<ParkingFloor> getAllParkingFloor() {
        return parkingFloorRepository.findAll();
    }


    public Integer updateParkingFloorStatus(String floorId, FloorStatus floorStatus) {
        Integer updatedRecordCount = Integer.valueOf(parkingFloorRepository.updateParkingFloorStatus(floorId, floorStatus));
        log.info("Updated record count = {}", updatedRecordCount);
        return updatedRecordCount;
    }

    public Integer updateMultipleParkingFloorStatus(List<String> floorIdList, FloorStatus floorStatus) {
        Integer updateRecord=Integer.valueOf(
                parkingFloorRepository.updateMultipleParkingFloorStatus(floorIdList, floorStatus));
        log.info("updated multiple parking status count={}",updateRecord);
        return updateRecord;
    }

}
