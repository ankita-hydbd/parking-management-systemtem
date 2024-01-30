package com.example.parkingmanagementsystem.parking.floor;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor,String> {

    @Modifying
    @Query("UPDATE ParkingFloor p SET p.status = :status WHERE p.floorId = :floorId")
    @Transactional
    String updateParkingFloorStatus(
            @Param("floorId") String floorId,
            @Param("status") FloorStatus status
    );

    @Modifying
    @Query("update ParkingFloor p set p.status= :status  where p.floorId IN :floorIdList")
    @Transactional
    int updateMultipleParkingFloorStatus(
            @Param("floorIdList") List<String> floorId,
            @Param("status") FloorStatus status
    );

}
