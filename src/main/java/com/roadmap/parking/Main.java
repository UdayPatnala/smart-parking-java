package com.roadmap.parking;

public class Main {
    public static void main(String[] args) {
        SmartParkingSystem system = new SmartParkingSystem();
        system.addSlot(new ParkingSlot(1, VehicleType.CAR));
        system.addSlot(new ParkingSlot(2, VehicleType.CAR));
        system.addSlot(new ParkingSlot(10, VehicleType.BIKE));

        Ticket ticket = system.park(new Vehicle("KA01AB1234", VehicleType.CAR));
        System.out.println("Parked at slot: " + ticket.getSlot().getSlotNumber());

        ParkingSlot released = system.unpark(ticket.getTicketId());
        System.out.println("Released slot: " + released.getSlotNumber());
    }
}
