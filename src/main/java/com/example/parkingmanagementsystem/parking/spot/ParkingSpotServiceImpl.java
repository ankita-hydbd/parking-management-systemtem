package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.lot.exception.ParkingLotNotFoundException;
import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import lombok.SneakyThrows;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService{
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository=parkingSpotRepository;
    }


    @Override
    public String createParkingSpot(ParkingSpot parkingSpot) {
       parkingSpotRepository.save(parkingSpot);
        return "successfully created";
    }

    @Override
    public String updateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotRepository.save(parkingSpot);
        return "successfully updated";
    }

    @Override
    public String deleteParkingSpot(String spotId) {
        parkingSpotRepository.deleteById(spotId);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public ParkingSpot getParkingSpot(String spotId)  {
        if(parkingSpotRepository.findById(spotId).isEmpty())
            throw new ParkingLotNotFoundException("ParkingSpot detail doesn't exist");

        return parkingSpotRepository.findById(spotId).get();
    }

    @Override
    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotRepository.findAll();
    }

    @Override
    public ParkingSpot findAvailableSpotByLotAndType(ParkingRequest parkingRequest) {
        ParkingSpot availableParkingSpot = parkingSpotRepository.findAvailableSpotByLotIdAndSpotType(
                parkingRequest.getLotId(),
                parkingRequest.getParkingSpotType(),
                true,
                Limit.of(1)
        );
        return availableParkingSpot;
    }

    @Override
    public Integer updateParkingSpotStatus(String spotId, Boolean isFree) {
        return parkingSpotRepository.updateParkingSpotStatus(spotId, isFree);
    }

}
