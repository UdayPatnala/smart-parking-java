package com.roadmap.parking;

public class SmartParkingSystemTest {

    public static void main(String[] args) {
        shouldAllocateNearestSlotAndReleaseIt();
        shouldFailWhenNoSlotAvailable();
        System.out.println("All SmartParkingSystem tests passed.");
    }

    private static void shouldAllocateNearestSlotAndReleaseIt() {
        SmartParkingSystem system = new SmartParkingSystem();
        system.addSlot(new ParkingSlot(5, VehicleType.CAR));
        system.addSlot(new ParkingSlot(2, VehicleType.CAR));

        Ticket ticket = system.park(new Vehicle("DL01AA1111", VehicleType.CAR));
        assertEquals(2, ticket.getSlot().getSlotNumber(), "Expected nearest slot allocation");

        system.unpark(ticket.getTicketId());
        assertEquals(2, system.getAvailableSlotCount(VehicleType.CAR), "Expected all slots available after unpark");
    }

    private static void shouldFailWhenNoSlotAvailable() {
        SmartParkingSystem system = new SmartParkingSystem();
        assertThrows(IllegalStateException.class,
                () -> system.park(new Vehicle("MH20XY9999", VehicleType.BIKE)),
                "Expected parking to fail when no slots are available");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ". Expected " + expected + " but got " + actual);
        }
    }

    private static void assertThrows(Class<? extends Throwable> expectedType, Runnable action, String message) {
        try {
            action.run();
        } catch (Throwable throwable) {
            if (expectedType.isInstance(throwable)) {
                return;
            }
            throw new AssertionError(message + ". Expected " + expectedType.getSimpleName() +
                    " but got " + throwable.getClass().getSimpleName());
        }
        throw new AssertionError(message + ". Expected exception " + expectedType.getSimpleName() + " but none was thrown");
    }
}