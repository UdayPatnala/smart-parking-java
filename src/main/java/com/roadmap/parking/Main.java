package com.roadmap.parking;

public class Main {
    public static void main(String[] args) {
        SmartParkingSystem system = new SmartParkingSystem();

        System.out.println("=== Smart Parking System Demo ===");

        system.addSlot(new ParkingSlot(1, VehicleType.CAR));
        system.addSlot(new ParkingSlot(2, VehicleType.CAR));
        system.addSlot(new ParkingSlot(5, VehicleType.CAR));
        system.addSlot(new ParkingSlot(10, VehicleType.BIKE));
        system.addSlot(new ParkingSlot(11, VehicleType.BIKE));
        system.addSlot(new ParkingSlot(20, VehicleType.TRUCK));

        System.out.println("Added 3 CAR slots, 2 BIKE slots, 1 TRUCK slot");

        Ticket t1 = system.park(new Vehicle("KA01AB1234", VehicleType.CAR));
        System.out.println("Parked KA01AB1234 (CAR) at slot " + t1.getSlot().getSlotNumber());

        Ticket t2 = system.park(new Vehicle("KA05XY9876", VehicleType.CAR));
        System.out.println("Parked KA05XY9876 (CAR) at slot " + t2.getSlot().getSlotNumber());

        Ticket t3 = system.park(new Vehicle("KA09MN5555", VehicleType.BIKE));
        System.out.println("Parked KA09MN5555 (BIKE) at slot " + t3.getSlot().getSlotNumber());

        System.out.println("Available CAR slots: " + system.getAvailableSlotCount(VehicleType.CAR));
        System.out.println("Available BIKE slots: " + system.getAvailableSlotCount(VehicleType.BIKE));

        ParkingSlot released = system.unpark(t1.getTicketId());
        System.out.println("Released slot " + released.getSlotNumber() + " (was KA01AB1234)");

        System.out.println("Available CAR slots: " + system.getAvailableSlotCount(VehicleType.CAR));
        System.out.println("Active tickets: " + system.getActiveTicketCount());
    }
}
