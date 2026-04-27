package com.roadmap.parking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingSystemTest {

    @Test
    void shouldAllocateNearestSlotAndReleaseIt() {
        SmartParkingSystem system = new SmartParkingSystem();
        system.addSlot(new ParkingSlot(5, VehicleType.CAR));
        system.addSlot(new ParkingSlot(2, VehicleType.CAR));

        Ticket ticket = system.park(new Vehicle("DL01AA1111", VehicleType.CAR));
        assertEquals(2, ticket.getSlot().getSlotNumber());

        system.unpark(ticket.getTicketId());
        assertEquals(2, system.getAvailableSlotCount(VehicleType.CAR));
    }

    @Test
    void shouldFailWhenNoSlotAvailable() {
        SmartParkingSystem system = new SmartParkingSystem();
        assertThrows(IllegalStateException.class,
                () -> system.park(new Vehicle("MH20XY9999", VehicleType.BIKE)));
    }
}
