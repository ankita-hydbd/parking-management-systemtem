package com.example.parkingmanagementsystem.parking.rate;



import com.example.parkingmanagementsystem.parking.rate.exception.ParkingRateNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ParkingRateServiceImpl implements ParkingRateService {
    private ParkingRateRepository parkingRateRepository;

    public ParkingRateServiceImpl(ParkingRateRepository parkingRateRepository) {
        this.parkingRateRepository = parkingRateRepository;
    }


    @Override
    public String createParkingRate(ParkingRate parkingRate) {
        parkingRateRepository.save(parkingRate);
        return "successfully created";
    }

    @Override
    public String updateParkingRate(ParkingRate parkingRate) {
        parkingRateRepository.save(parkingRate);
        return "successfully updated";
    }

    @Override
    public String deleteParkingRate(String tokenNumber) {
        parkingRateRepository.deleteById(tokenNumber);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingRate getParkingRate(String hourlyRate)  {
        if(parkingRateRepository.findById(hourlyRate).isEmpty())
            throw new ParkingRateNotFoundException("ParkingRate detail doesn't exist");

        return  parkingRateRepository.findById(hourlyRate).get();
    }

    @Override
    public List<ParkingRate> getAllParkingRate() {
        return parkingRateRepository.findAll();
    }
}
