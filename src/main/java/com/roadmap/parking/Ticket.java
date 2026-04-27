package com.roadmap.parking;

import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final ParkingSlot slot;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;

    public Ticket(String ticketId, ParkingSlot slot, Vehicle vehicle, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.slot = slot;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
