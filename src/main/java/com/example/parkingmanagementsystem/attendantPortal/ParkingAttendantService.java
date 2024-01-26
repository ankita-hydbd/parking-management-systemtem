package com.example.parkingmanagementsystem.attendantPortal;

import java.util.List;

public interface ParkingAttendantService {
    public String createParkingAttendant(ParkingAttendant parkingAttendant);
    public String updateParkingAttendant(ParkingAttendant parkingAttendant);
    public String deleteParkingAttendant(String attendantId);
    public ParkingAttendant getParkingAttendant(String attendantId);
    public List<ParkingAttendant> getAllParkingAttendant();

}
