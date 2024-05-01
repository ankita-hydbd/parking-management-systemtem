package com.example.parkingmanagementsystem.parking.ticket.book;

import com.example.parkingmanagementsystem.config.ParkingManagementProperties;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpot;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotService;
import com.example.parkingmanagementsystem.parking.spot.ParkingSpotType;
import com.example.parkingmanagementsystem.parking.ticket.ParkingSpotCountRequest;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicket;
import com.example.parkingmanagementsystem.parking.ticket.ParkingTicketService;
import com.example.parkingmanagementsystem.parking.ticket.TicketStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BatchBookTicketControllerTest {

    @Mock
    private ParkingSpotService parkingSpotService;
    @Mock
    private ParkingTicketService parkingTicketService;
    @Mock
    private ParkingManagementProperties properties;
    @InjectMocks
    private BatchBookTicketController controller;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testBookBulkParkingSpots_successfulCase() {
        System.out.println(controller);
        final ParkingSpotCountRequest request = createParkingSpotCountRequest();
        final List<ParkingSpot> availableParkingSpotList =
                List.of(createAvailableParkingSpot());

        Mockito.when(parkingSpotService.getAvailableSpotType(request))
                .thenReturn(availableParkingSpotList);

        List<String> freeSpotIds = List.of("s1");
        Mockito.when(
                parkingSpotService.updateBatchBookSpotAvailability(false, true, freeSpotIds))
                .thenReturn(1);

        List<ParkingTicket> stagedParkingTicketList = List.of(createStagedParkingTicket());
        Mockito.when(parkingTicketService.createParkingTicketBatch(Mockito.anyList()))
                        .thenReturn(stagedParkingTicketList);

        BulkTicketBookResponse response =
                controller.bookBulkParkingSpots(request);

        /////////////// Verifications
        Mockito.verify(parkingSpotService, Mockito.times(1))
                .getAvailableSpotType(request);

        Mockito.verify(parkingSpotService, Mockito.times(1))
                .updateBatchBookSpotAvailability(false, true, freeSpotIds);

        ArgumentCaptor<List<ParkingTicket>> parkingTicketListArgCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(parkingTicketService, Mockito.times(1))
                .createParkingTicketBatch(parkingTicketListArgCaptor.capture());
        List<ParkingTicket> actualStagedParkingTicketList = parkingTicketListArgCaptor.getValue();
        verifyParkingTicketList(stagedParkingTicketList, actualStagedParkingTicketList);
        Assertions.assertNull(actualStagedParkingTicketList.get(0).getTokenNumber());

        Assertions.assertNotNull(response);

        Assertions.assertEquals(stagedParkingTicketList.size(),
                response.getParkingTicketList().size());
        verifyParkingTicketList(stagedParkingTicketList,
                response.getParkingTicketList());
        Assertions.assertEquals("ticket-1",
                response.getParkingTicketList().get(0).getTokenNumber());
        Assertions.assertNotNull(response.getBookedSummary());
        Assertions.assertEquals(1,
                response.getBookedSummary().getBookedIntegerCount());
    }

    private static void verifyParkingTicketList(
            List<ParkingTicket> stagedParkingTicketList,
            List<ParkingTicket> actualParkingTicketList) {
        Assertions.assertEquals(stagedParkingTicketList.size(), actualParkingTicketList.size());
        ParkingTicket actualStagedParkingTicket = actualParkingTicketList.get(0);
        ParkingTicket expectedStagedParkingTicket = stagedParkingTicketList.get(0);
        Assertions.assertEquals(expectedStagedParkingTicket.getSpotId(),
                actualStagedParkingTicket.getSpotId());
        Assertions.assertEquals(expectedStagedParkingTicket.getTicketStatus(),
                actualStagedParkingTicket.getTicketStatus());
        Assertions.assertEquals(expectedStagedParkingTicket.getVehicleNumber(),
                actualStagedParkingTicket.getVehicleNumber());
    }

    private static ParkingTicket createStagedParkingTicket() {
        return ParkingTicket.builder()
                .tokenNumber("ticket-1")
                .vehicleNumber("BULK_BOOKING")
                .spotId("s1")
                .ticketStatus(TicketStatus.ACTIVE)
                .startTime(LocalDateTime.now())
                .build();
    }

    private static ParkingSpot createAvailableParkingSpot() {
        return ParkingSpot.builder()
                .spotId("s1")
                .lotId("l1")
                .floorId("f1")
                .parkingSpotType(ParkingSpotType.MOTORBIKE)
                .isFree(Boolean.TRUE)
                .build();
    }

    private static ParkingSpotCountRequest createParkingSpotCountRequest() {
        return ParkingSpotCountRequest.builder()
                .parkingSpotType(ParkingSpotType.MOTORBIKE)
                .count(1)
                .build();
    }
}
