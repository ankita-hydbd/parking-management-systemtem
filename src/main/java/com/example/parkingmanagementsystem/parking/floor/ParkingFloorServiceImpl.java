package com.example.parkingmanagementsystem.parking.floor;

import com.example.parkingmanagementsystem.parking.floor.exception.ParkingFloorNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public  class ParkingFloorServiceImpl implements ParkingFloorService{
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
    public ParkingFloor getParkingFloor(String floorId)  {
        if(parkingFloorRepository.findById(floorId).isEmpty())
            throw new ParkingFloorNotFoundException("ParkingFloor detail doesn't exist");

        return parkingFloorRepository.findById(floorId).get();
    }

    @Override
    public List<ParkingFloor> getAllParkingFloor() {
        return parkingFloorRepository.findAll();
    }

}
