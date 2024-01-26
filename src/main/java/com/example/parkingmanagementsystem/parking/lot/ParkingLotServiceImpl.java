package com.example.parkingmanagementsystem.parking.lot;

import com.example.parkingmanagementsystem.parking.lot.exception.ParkingLotNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {


    private ParkingLotRepository parkingLotRepository;
    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }


    @Override
    public String createParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
        return "successfully created";
    }

    @Override
    public String updateParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
        return "successfully updated";
    }

    @Override
    public String deleteParkingLot(String lotId) {
      parkingLotRepository.deleteById(lotId);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingLot getParkingLot(String lotId)  {
        if(parkingLotRepository.findById(lotId).isEmpty())
            throw new ParkingLotNotFoundException("ParkingLot detail doesn't exist");

        return  parkingLotRepository.findById(lotId).get();
    }

    @Override
    public List<ParkingLot> getAllParkingLot() {
        return parkingLotRepository.findAll();
    }
}
