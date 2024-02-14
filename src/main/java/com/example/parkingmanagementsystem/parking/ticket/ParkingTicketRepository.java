package com.example.parkingmanagementsystem.parking.ticket;

import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, String> {

//    @Transactional
//    @Modifying
//    @Query("update ParkingSpot p set p.isFree=isFreeUpdated where p.isFree=isFreeExisting and p.spotId :spotIdList")
//    Integer updateParkingSpotAvailability(
//            @Param("spotId") List<String> spotId,
//            @Param("isFreeUpdated") Boolean isFreeUpdated,
//            @Param("isFreeExisting") Boolean isFreeExisting
//
//    );


//    @Query("update ParkingSpot p set p.isFree=false where p.isFree=true and p.spotId in ('S5','S6')")
//
//    @Query("insert into ParkingTicket(tokenNumber,startTime,spotId,vehicleNumber)" +
//            " values (UUID(),NOW(),'S5','BulkBooking'),
//             (UUID(), NOW(), 'S6', 'BulkBooking')"

}

