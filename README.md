# Smart Parking System — Java

A parking slot allocation system demonstrating OOP design, data structures (HashMap, PriorityQueue), and clean architecture. Built with pure Java — no Maven, no external dependencies.

## Design

```
  ┌────────────────────────────────────────────┐
  │           SmartParkingSystem                │
  │                                             │
  │  EnumMap<VehicleType, PriorityQueue<Slot>>  │
  │  ┌───────────────────────────────────┐      │
  │  │ CAR  → PQ[slot1, slot2, slot5]    │      │
  │  │ BIKE → PQ[slot10, slot11]         │      │
  │  │ TRUCK→ PQ[slot20]                 │      │
  │  └───────────────────────────────────┘      │
  │                                             │
  │  HashMap<ticketId, Ticket>  (active)        │
  └────────────────────────────────────────────┘

  park(vehicle)   → allocates nearest available slot → returns Ticket
  unpark(ticket)  → releases slot back to the queue
```

## How Allocation Works

1. Slots are stored in a **PriorityQueue** ordered by slot number (nearest first)
2. `park()` polls the smallest-numbered slot for the vehicle type
3. `unpark()` returns the slot to the queue for reuse
4. All operations run in **O(log n)** time

## Vehicle Types

| Type | Description |
|------|-------------|
| `CAR` | Standard car slot |
| `BIKE` | Two-wheeler slot |
| `TRUCK` | Large vehicle slot |

## Run Demo

```powershell
.\run.ps1
```

## Run Tests

```powershell
.\run.ps1 -Test
```

## Example Output

```
=== Smart Parking System Demo ===
Added 3 CAR slots, 2 BIKE slots, 1 TRUCK slot
Parked KA01AB1234 (CAR) at slot 1
Parked KA05XY9876 (CAR) at slot 2
Parked KA09MN5555 (BIKE) at slot 10
Available CAR slots: 1
Available BIKE slots: 1
Released slot 1 (was KA01AB1234)
Available CAR slots: 2
Active tickets: 2
```

## Project Structure

```
├── src/
│   ├── main/java/com/roadmap/parking/
│   │   ├── SmartParkingSystem.java     # Core allocation engine
│   │   ├── ParkingSlot.java            # Slot with number + type
│   │   ├── Vehicle.java                # Vehicle with registration + type
│   │   ├── VehicleType.java            # Enum: CAR, BIKE, TRUCK
│   │   ├── Ticket.java                 # Parking ticket record
│   │   └── Main.java                   # Demo driver
│   └── test/java/com/roadmap/parking/
│       └── SmartParkingSystemTest.java  # Unit tests
├── run.ps1                              # Compile & run script
└── README.md
```

## Key Concepts

- **OOP**: encapsulation, composition, enum types
- **Data structures**: EnumMap, PriorityQueue, HashMap
- **Allocation strategy**: nearest-slot-first via min-heap
- **Immutable design**: Vehicle, ParkingSlot, Ticket use final fields

## License

MIT