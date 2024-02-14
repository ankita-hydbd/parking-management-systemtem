package com.example.parkingmanagementsystem.parking.spot;

import com.example.parkingmanagementsystem.parking.floor.FloorStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

//    /**
//     * Reference - https://www.baeldung.com/jpa-limit-query-results
//     *
//     * @param lotId
//     * @param parkingSpotType
//     * @param isFree
//     * @return
//     */
    //@Query("SELECT p FROM ParkingSpot p WHERE p.lotId = :lotId AND p.parkingSpotType = :parkingSpotType AND p.isFree = :isFree AND p.FloorStatus=FloorStatus")
//    ParkingSpot findAvailableFloorIdSpotIdBySpotTypeV1(
//            @Param("lotId") String lotId,
//            @Param("parkingSpotType") ParkingSpotType parkingSpotType,
//            @Param("isFree") Boolean isFree,
//            @Param("FloorStatus") FloorStatus status,
//            @Param("floorIdList") String floorIdList,
//            @Param("spotId") String spotId
//    );

//    Reference -https://www.baeldung.com/jpa-transaction-required-exception
//
//    @param
//    spotId
//    @param isFree
//      @return

    @Modifying
    @Query("UPDATE ParkingSpot p SET p.isFree = :isFree WHERE p.spotId = :spotId")
    @Transactional
    Integer updateParkingSpotStatus(
            @Param("spotId") String spotId,
            @Param("isFree") Boolean isFree
    );


//    @Query(value = "SELECT * FROM ParkingRequest pr WHERE " +
//            "EXISTS (SELECT 1 FROM ParkingFloor pf WHERE pf.floorIdList = pr.floorIdList AND pf.FloorStatus = :FloorStatus) " +
//            "AND EXISTS (SELECT 1 FROM ParkingSpot ps WHERE ps.SpotId = pr.SpotId AND ps.parkingSpotType = :parkingSpotType " +
//            "AND ps.isFree = :isFree AND ps.lotId = :lotId  )")

    /*
    select ps.*
from parking_floor pf,parking_spot ps
where ps.floor_id=pf.floor_id
and ps.is_free='true' and pf.status='AVAILABLE' AND ps.parking_spot_type='COMPACT';
     */
    @Query(nativeQuery = true,
            value = "select ps.* " +
                    "from parking_floor pf,parking_spot ps " +
                    "where ps.floor_id=pf.floor_id " +
                    "and ps.is_free= :isFree and pf.status= :status AND ps.parking_spot_type= :parkingSpotType")
    List<ParkingSpot> findAvailableFloorIdSpotIdBySpotType(
//            String lotId,
            @Param("parkingSpotType") String parkingSpotType,
            @Param("status") String status,
//            String floorIdList,
            @Param("isFree") Boolean isFree);
//            String spotId);


    @Transactional
    @Query("SELECT p.parkingSpotType, p.floorId, COUNT(*) AS availableCount " +
            "FROM ParkingSpot p " +
            "WHERE p.isFree = true " +
            "GROUP BY p.parkingSpotType, p.floorId")
    List<Object[]> CountAvailableSpotType(
//            @Param("floorId") String floorId,
//            @Param("ParkingSpotType") ParkingSpotType parkingSpotType,
//
    );


    @Query(value = "select p.* from parking_spot p where p.is_free=:isFree and p.parking_spot_type =:parkingSpotType " +
            " limit :count", nativeQuery = true)
    List<ParkingSpot> getAvailableSpotType(
            @Param("isFree") Boolean isFree,
            @Param("count") int count,
            @Param("parkingSpotType") String parkingSpotType
    );

    @Query(value = "select p from ParkingSpot p where p.isFree=:isFree and p.parkingSpotType =:parkingSpotType")
    List<ParkingSpot> getAvailableSpotType(
            @Param("isFree") Boolean isFree,
            @Param("parkingSpotType") ParkingSpotType parkingSpotType,
            final Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ParkingSpot p set p.isFree=:isFreeUpdated where p.isFree=:isFreeExisting " +
            "and p.spotId in :spotIdList")
    Integer updateBatchBookSpotAvailability(
            @Param("isFreeUpdated") Boolean isFreeUpdated,
            @Param("isFreeExisting") Boolean isFreeExisting,
            @Param("spotIdList") List<String> spotIdList
    );



}

  

   
