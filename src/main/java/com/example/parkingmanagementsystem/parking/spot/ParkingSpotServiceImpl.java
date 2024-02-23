package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.lot.exception.ParkingLotNotFoundException;
import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
class ParkingSpotServiceImpl implements ParkingSpotService {
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
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
    public ParkingSpot getParkingSpot(String spotId) {
        if (parkingSpotRepository.findById(spotId).isEmpty())
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

    public List<Object[]> CountAvailableSpotType() {
        return parkingSpotRepository.CountAvailableSpotType();
    }


    public List<ParkingSpot> getAvailableSpotType(ParkingSpotCountRequest parkingSpotCountRequest) {
        List<ParkingSpot> availableSpotType =
//                parkingSpotRepository.getAvailableSpotType(parkingSpotCountRequest.getIsFree(),
//                        parkingSpotCountRequest.getParkingSpotType(),
//                        PageRequest.of(0, parkingSpotCountRequest.getCount()));

//                parkingSpotRepository.getAvailableSpotType(
//                        true,
//                        ParkingSpotType.valueOf(parkingSpotCountRequest.getParkingSpotType().name()),
//                        parkingSpotCountRequest.getCount()
//                );

                parkingSpotRepository.getAvailableSpotType(
                        true,
                        ParkingSpotType.valueOf(parkingSpotCountRequest.getParkingSpotType().name()),
                        PageRequest.of(0, parkingSpotCountRequest.getCount())
                );

        log.info("get available spot type (using pagination) ={}", availableSpotType);
        return availableSpotType;
    }


    public Integer updateBatchBookSpotAvailability(Boolean isFreeUpdated,
                                                   Boolean isFreeExisting, List<String> spotIdList) {
        return parkingSpotRepository.updateBatchBookSpotAvailability(isFreeUpdated, isFreeExisting, spotIdList);
    }

    public List<ParkingSpot> createParkingSpotBatch(List<ParkingSpot> parkingSpotList)
    {
        return parkingSpotRepository.saveAll(parkingSpotList);
    }


    @Override
    public List<ParkingSpot> getAllParkingSpotByIds(List<String> spotIds) {
        return parkingSpotRepository.findAllById(spotIds);
    }

}