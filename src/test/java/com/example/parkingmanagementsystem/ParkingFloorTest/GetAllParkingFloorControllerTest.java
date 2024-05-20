package com.example.parkingmanagementsystem.ParkingFloorTest;

import com.example.parkingmanagementsystem.parking.floor.ParkingFloor;
import com.example.parkingmanagementsystem.parking.floor.ParkingFloorController;
import com.example.parkingmanagementsystem.parking.floor.ParkingFloorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.example.parkingmanagementsystem.parking.floor.FloorStatus.AVAILABLE;
import static com.example.parkingmanagementsystem.parking.floor.FloorStatus.UNAVAILABLE;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllParkingFloorControllerTest {
    @Mock
    private ParkingFloorService parkingFloorService;
    @InjectMocks
    private ParkingFloorController parkingFloorController;
    private List<ParkingFloor> parkingFloorList;

    @BeforeEach
    public void setup() {
        parkingFloorList = Arrays.asList(new ParkingFloor("F1", "L1", AVAILABLE),
                new ParkingFloor("F2", "L2", AVAILABLE),
                new ParkingFloor("F3", "L3", UNAVAILABLE));
    }

    @Test
    public void testGetAllParkingFloor() {
        when(parkingFloorService.getAllParkingFloor()).thenReturn(parkingFloorList);
        List<ParkingFloor> result = parkingFloorController.getAllParkingFloor();
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(parkingFloorList.get(0), result.get(0));
        Assertions.assertEquals(parkingFloorList.get(1), result.get(1));
        Assertions.assertEquals(parkingFloorList.get(2), result.get(2));
    }
}
