package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.spot.dto.ParkingSpotDTO;
import com.example.parkingmanagementsystem.parking.spot.model.ParkingRequest;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ParkingSpot")
@Log4j2
public class ParkingSpotController {

    private final ModelMapper modelMapper;
    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ModelMapper modelMapper, ParkingSpotService parkingSpotService) {
        this.modelMapper = modelMapper;
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping("/{spotId}")
    public ParkingSpot getParkingSpot(@PathVariable("SpotId") String spotId) {
        log.info("Received spotId = {}", spotId);
        return parkingSpotService.getParkingSpot(spotId);
    }

    @GetMapping()
    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotService.getAllParkingSpot();
    }

    @PostMapping
    public String createParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        log.info("Received lotId details = {}", parkingSpot);
        parkingSpotService.createParkingSpot(parkingSpot);
        return "Parking spot created successfully";
    }

    @PutMapping
    public String updateParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        parkingSpotService.updateParkingSpot(parkingSpot);
        return "Parking spot updated successfully";
    }

    @DeleteMapping("{spotId}")
    public String deleteParkingSpot(@PathVariable("spotId") String spotId) {
        parkingSpotService.deleteParkingSpot(spotId);

        return "Parking spot deleted successfully";
    }

    @GetMapping("/FindAvailableSpot")
    public List<ParkingSpot> findAvailableSpot(@RequestBody final ParkingRequest parkingRequest) {
        log.info("Received parking request = {}", parkingRequest);
        return parkingSpotService.findAvailableFloorIdSpotIdBySpotType(parkingRequest);
    }

    @GetMapping("/v2")
    public List<ParkingSpotDTO> getAllParkingSpotV2() {
        return parkingSpotService.getAllParkingSpot().stream()
                .map(parkingSpot -> modelMapper.map(parkingSpot,ParkingSpotDTO.class))
                    .collect(Collectors.toList());

    }

    @GetMapping("/SpotCount")
    public List<Object[]> CountAvailableSpotType()
    {
        log.info("successfully found spot availability count={} ");
        return parkingSpotService.CountAvailableSpotType();
    }
}
