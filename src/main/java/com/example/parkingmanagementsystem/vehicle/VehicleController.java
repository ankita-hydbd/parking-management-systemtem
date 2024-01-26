//package com.example.parkingmanagementsystem.vehicle;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Vehicle")
//@Log4j2
//public class VehicleController {
//
//       VehicleService vehicleService;
//
//    public VehicleController(VehicleService vehicleService) {
//        this.vehicleService = vehicleService;
//    }
//
//
//    @GetMapping("/{userName}")
//        public  Vehicle getVehicle(@PathVariable("userName") String userName) {
//            log.info("Received userName = {}", userName);
//            return vehicleService.getVehicle(userName);
//        }
//
//        @GetMapping()
//        public List< Vehicle> getAllVehicle() {
//            return vehicleService.getAllVehicle();
//        }
//
//        @PostMapping
//        public String createVehicle(@RequestBody Vehicle vehicle) {
//            log.info("Received  Vehicle details = {}", vehicle);
//            vehicleService.createVehicle(vehicle);
//            return "Vehicle created successfully";
//        }
//
//        @PutMapping
//        public String updateVehicle(@RequestBody Vehicle vehicle) {
//            vehicleService.updateVehicle(vehicle);
//            return "Vehicle updated successfully";
//        }
//
//        @DeleteMapping("{licenceNumber}")
//        public String deleteVehicle(@PathVariable("licenceNumber") String licenceNumber) {
//            vehicleService.deleteVehicle(licenceNumber);
//
//            return "Vehicle deleted successfully";
//        }
//    }
//
//
