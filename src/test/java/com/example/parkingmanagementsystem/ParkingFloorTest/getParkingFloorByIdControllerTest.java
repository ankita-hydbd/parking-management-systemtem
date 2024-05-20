package com.example.parkingmanagementsystem.ParkingFloorTest;

import com.example.parkingmanagementsystem.parking.floor.FloorStatus;
import com.example.parkingmanagementsystem.parking.floor.ParkingFloor;
import com.example.parkingmanagementsystem.parking.floor.ParkingFloorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class getParkingFloorByIdControllerTest {

    @Test
    public void getParkingFloorTest()
    {
        ParkingFloorService service= Mockito.mock(ParkingFloorService.class);
        ParkingFloor parkingFloor=getParkingFloorById();
        when(service.getParkingFloor("F2")).thenReturn(parkingFloor);
        System.out.println(service.getParkingFloor("F2"));
    }

    public ParkingFloor getParkingFloorById()
    {
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setFloorId("F2");
        parkingFloor.setLotId("L1");
        parkingFloor.setStatus(FloorStatus.valueOf("AVAILABLE"));
        return parkingFloor;
    }


}


