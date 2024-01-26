package com.example.parkingmanagementsystem.parking.spot;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,String> {

    /**
     * Reference - https://www.baeldung.com/jpa-limit-query-results
     * @param lotId
     * @param parkingSpotType
     * @param isFree
     * @param rowCountLimit
     * @return
     */
    @Query("SELECT p FROM ParkingSpot p WHERE p.lotId = :lotId AND p.parkingSpotType = :parkingSpotType AND p.isFree = :isFree")
    ParkingSpot findAvailableSpotByLotIdAndSpotType(
            @Param("lotId") String lotId,
            @Param("parkingSpotType") ParkingSpotType parkingSpotType,
            @Param("isFree") Boolean isFree,
            Limit rowCountLimit
    );

    /**
     * Reference - https://www.baeldung.com/jpa-transaction-required-exception
     * @param spotId
     * @param isFree
     * @return
     */
    @Modifying
    @Query("UPDATE ParkingSpot p SET p.isFree = :isFree WHERE p.spotId = :spotId")
    @Transactional
    Integer updateParkingSpotStatus(
            @Param("spotId") String spotId,
            @Param("isFree") Boolean isFree
    );
}
