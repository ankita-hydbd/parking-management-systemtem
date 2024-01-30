package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.lot.exception.ParkingLotNotFoundException;
import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
class ParkingSpotServiceImpl implements ParkingSpotService{
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
    public List<ParkingSpot> findAvailableFloorIdSpotIdBySpotType(ParkingRequest parkingRequest) {
        List<ParkingSpot> availableParkingFloorAndSpotList =
                parkingSpotRepository.findAvailableFloorIdSpotIdBySpotType(
//                        parkingRequest.getLotId(),
                        parkingRequest.getParkingSpotType().name(),
                        parkingRequest.getStatus().name(),
                        //parkingRequest.getFloorIdList(),
                        parkingRequest.getIsFree());
                        //parkingRequest.getSpotId());
        log.info("Query Result = {}", availableParkingFloorAndSpotList);
        return availableParkingFloorAndSpotList;
    }

    @Override
    public Integer updateParkingSpotStatus(String spotId, Boolean isFree) {
        return parkingSpotRepository.updateParkingSpotStatus(spotId, isFree);
    }

}
