//package com.example.parkingmanagementsystem.vehicle;
//
//import com.example.parkingmanagementsystem.vehicle.exception.VehicleNotFoundException;
//import lombok.SneakyThrows;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//@Service
//
//public class VehicleServiceImpl implements VehicleService{
//    private VehicleRepository vehicleRepository;
//
//    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
//        this.vehicleRepository = vehicleRepository;
//    }
//
//
//    @Override
//    public String createVehicle(Vehicle vehicle) {
//        vehicleRepository.save(vehicle);
//        return "successfully created";
//    }
//
//    @Override
//    public String updateVehicle(Vehicle vehicle) {
//        vehicleRepository.save(vehicle);
//        return "successfully updated";
//    }
//
//    @Override
//    public String deleteVehicle(String userName) {
//       vehicleRepository.deleteById(userName);
//        return "successfully deleted";
//    }
//
//    @SneakyThrows
//    @Override
//    public Vehicle getVehicle(String licenceNumber)  {
//        if(vehicleRepository.findById(licenceNumber).isEmpty())
//            throw new VehicleNotFoundException("Vehicle detail doesn't exist");
//
//        return  vehicleRepository.findById(licenceNumber).get();
//    }
//
//    @Override
//    public List<Vehicle> getAllVehicle() {
//        return vehicleRepository.findAll();
//    }
//}
