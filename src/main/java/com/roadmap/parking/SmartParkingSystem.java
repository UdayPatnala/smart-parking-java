package com.roadmap.parking;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public class SmartParkingSystem {
    private final Map<VehicleType, PriorityQueue<ParkingSlot>> availableSlots = new EnumMap<>(VehicleType.class);
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public SmartParkingSystem() {
        for (VehicleType type : VehicleType.values()) {
            availableSlots.put(type, new PriorityQueue<>(Comparator.comparingInt(ParkingSlot::getSlotNumber)));
        }
    }

    public void addSlot(ParkingSlot slot) {
        availableSlots.get(slot.getSupportedType()).offer(slot);
    }

    public Ticket park(Vehicle vehicle) {
        PriorityQueue<ParkingSlot> slots = availableSlots.get(vehicle.getType());
        if (slots == null || slots.isEmpty()) {
            throw new IllegalStateException("No available slot for vehicle type: " + vehicle.getType());
        }

        ParkingSlot slot = slots.poll();
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), slot, vehicle, LocalDateTime.now());
        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public ParkingSlot unpark(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket id");
        }

        ParkingSlot slot = ticket.getSlot();
        availableSlots.get(slot.getSupportedType()).offer(slot);
        return slot;
    }

    public int getAvailableSlotCount(VehicleType type) {
        PriorityQueue<ParkingSlot> slots = availableSlots.get(type);
        return slots == null ? 0 : slots.size();
    }
}
