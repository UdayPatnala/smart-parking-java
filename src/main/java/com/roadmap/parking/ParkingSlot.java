package com.roadmap.parking;

public class ParkingSlot {
    private final int slotNumber;
    private final VehicleType supportedType;

    public ParkingSlot(int slotNumber, VehicleType supportedType) {
        this.slotNumber = slotNumber;
        this.supportedType = supportedType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public VehicleType getSupportedType() {
        return supportedType;
    }
}
