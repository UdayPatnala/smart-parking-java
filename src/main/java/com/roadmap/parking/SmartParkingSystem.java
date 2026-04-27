package com.roadmap.parking;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

/**
 * A smart parking system that allocates the nearest available slot
 * for a given vehicle type using a PriorityQueue (min-heap by slot number).
 */
public class SmartParkingSystem {
    private final Map<VehicleType, PriorityQueue<ParkingSlot>> availableSlots = new EnumMap<>(VehicleType.class);
    private final Map<String, Ticket> activeTickets = new HashMap<>();

    public SmartParkingSystem() {
        for (VehicleType type : VehicleType.values()) {
            availableSlots.put(type, new PriorityQueue<>(Comparator.comparingInt(ParkingSlot::getSlotNumber)));
        }
    }

    /** Registers a new parking slot in the system. */
    public void addSlot(ParkingSlot slot) {
        availableSlots.get(slot.getSupportedType()).offer(slot);
    }

    /**
     * Parks a vehicle in the nearest available slot for its type.
     *
     * @param vehicle the vehicle to park
     * @return a parking ticket
     * @throws IllegalStateException if no slot is available
     */
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

    /**
     * Releases a parking slot and invalidates the ticket.
     *
     * @param ticketId the ticket to unpark
     * @return the released slot
     * @throws IllegalArgumentException if the ticket is invalid
     */
    public ParkingSlot unpark(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket id");
        }

        ParkingSlot slot = ticket.getSlot();
        availableSlots.get(slot.getSupportedType()).offer(slot);
        return slot;
    }

    /** Returns the number of available slots for a vehicle type. */
    public int getAvailableSlotCount(VehicleType type) {
        PriorityQueue<ParkingSlot> slots = availableSlots.get(type);
        return slots == null ? 0 : slots.size();
    }

    /** Returns the number of currently active (parked) tickets. */
    public int getActiveTicketCount() {
        return activeTickets.size();
    }
}
