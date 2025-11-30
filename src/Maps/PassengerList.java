package Maps;

import Characters.Driver;
import Utils.InputHandler;

import java.util.*;


public class PassengerList {
    private List<Passenger> passengers;
    private int passengerIdCounter;
    private Driver driver;
    private World gameMap; // Use World base class
    private boolean[] passengerSlots;
    private Passenger[] slotAssignments;

    public PassengerList(Driver driver, World gameMap) {
        this.passengers = new ArrayList<>();
        this.passengerIdCounter = 1;
        this.driver = driver;
        this.gameMap = gameMap;
        this.passengerSlots = new boolean[13];
        this.slotAssignments = new Passenger[13];
    }

    public static class Passenger {
        public int id;
        public String type;
        public int dropStop;
        public int fare;

        public Passenger(int id, String type, int dropStop, int fare) {
            this.id = id;
            this.type = type;
            this.dropStop = dropStop;
            this.fare = fare;
        }
    }
//=========================================================
    //below this all new code
    public void displayPassengerIndicator() {
        System.out.println("━━━━━━━━━━━━━━━");

        // Upper row (slots 0-5) - 6 slots
        System.out.print("");
        for (int i = 0; i < 6; i++) {
            System.out.print(passengerSlots[i] ? "[*]" : "[ ]");
        }
        System.out.println("#[Driver]");

        // Lower row (slots 6-11) - 6 slots + 1 slot next to driver (slot 12)
        System.out.print("");
        for (int i = 6; i < 12; i++) {
            System.out.print(passengerSlots[i] ? "[*]" : "[ ]");
        }
        System.out.println("#[" + (passengerSlots[12] ? "*" : " ") + "]");

        System.out.println("━━━━━━━━━━━━━━━");
    }

    // FIXED: Properly track passenger-slot relationships
    public void dropPassengerById(int passengerId, int currentStop) {
        Passenger target = null;
        int slotIndex = -1;

        // Find the passenger and their slot using the slotAssignments array
        for (int i = 0; i < slotAssignments.length; i++) {
            if (slotAssignments[i] != null && slotAssignments[i].id == passengerId) {
                target = slotAssignments[i];
                slotIndex = i;
                break;
            }
        }

        if (target != null && slotIndex != -1) {
            // Check if passenger can be dropped at this stop
            if (target.dropStop > currentStop) {
                System.out.println("❌ Cannot drop Passenger " + passengerId +
                        " - their drop stop is ahead (" + target.dropStop + ").");
                return;
            }
            // ADD CONFIRMATION PROMPT HERE
            int confirm = InputHandler.getChoice(
                    "Drop Passenger " + passengerId + "? (1 Yes | 2 No): ", 1, 2
            );

            if (confirm != 1) {
                System.out.println("❌ Drop cancelled for Passenger " + passengerId);
                return;
            }

            // Calculate fare and add to money
            int earnedFare = calculateAndAddFare(target, currentStop);

            // Remove passenger and free the slot
            passengers.remove(target);
            passengerSlots[slotIndex] = false;
            slotAssignments[slotIndex] = null; // Clear the slot assignment

            System.out.println("✅ Passenger " + passengerId + " dropped from slot " + (slotIndex + 1) +
                    ".");
        } else {
            System.out.println("❌ Passenger ID " + passengerId + " not found!");
        }
    }

    // FIXED: Now properly returns the passenger in a slot
   /*
    private Passenger getPassengerInSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < slotAssignments.length) {
            return slotAssignments[slotIndex];
        }
        return null;
    }
    */


    // FIXED: Now properly assigns passengers to slots with tracking
    public void addPassenger(Passenger passenger) {
        if (passengers.size() < 13) {
            // Find empty slots
            List<Integer> emptySlots = new ArrayList<>();
            for (int i = 0; i < passengerSlots.length; i++) {
                if (!passengerSlots[i]) {
                    emptySlots.add(i);
                }
            }

            if (!emptySlots.isEmpty()) {
                // Randomly select an empty slot
                Random rand = new Random();
                int slotIndex = emptySlots.get(rand.nextInt(emptySlots.size()));

                // Assign passenger to slot
                passengerSlots[slotIndex] = true;
                slotAssignments[slotIndex] = passenger; // Track which passenger is in this slot
                passengers.add(passenger);

                System.out.println("✅ Passenger " + passenger.id + " added to slot " + (slotIndex + 1));
            }
        }
    }

    //For GABA event
    // Add this method to your PassengerList class
    public void removeRandomPassenger() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers to remove!");
            return;
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(passengers.size());
        Passenger removedPassenger = passengers.get(randomIndex);

        // Find which slot this passenger occupies and free it
        for (int i = 0; i < slotAssignments.length; i++) {
            if (slotAssignments[i] == removedPassenger) {
                passengerSlots[i] = false;
                slotAssignments[i] = null;
                break;
            }
        }

        passengers.remove(randomIndex);
        System.out.println("❌ Passenger " + removedPassenger.id + " was removed due to the event!");
    }
    // Get current passenger count
    public int getPassengerCount() {
        return passengers.size();
    }

    // Check if jeepney is full
    public boolean isFull() {
        return passengers.size() >= 13;
    }

    // Get all passengers
    public List<Passenger> getPassengers() {
        return new ArrayList<>(passengers);
    }

    // FIXED: Improved passenger list display and dropping logic
    public boolean showPassengerList(int currentStop) {
        if (passengers.isEmpty()) {
            System.out.println("\nNo passengers onboard.");
            displayPassengerIndicator();
            return false;
        }

        boolean continueManaging = true;

        while (continueManaging && !passengers.isEmpty()) {
            passengers.sort(Comparator.comparingInt(p -> p.dropStop));

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("     PASSENGER LIST (STOP " + currentStop + ")");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            // Display the passenger indicator
            displayPassengerIndicator();

            System.out.println("ID  | Type      | Stop | Fare | Status");
            System.out.println("------------------------------------------");

            for (Passenger p : passengers) {
                String status = (p.dropStop <= currentStop) ? "READY TO DROP" : "WAITING";
                System.out.println(String.format("%-4d %-12s %-6d ₱%-5d %s",
                        p.id, p.type, p.dropStop, p.fare, status));
            }

            System.out.println("------------------------------------------");
            System.out.println("Passengers onboard: " + passengers.size());
            System.out.println("------------------------------------------");
            System.out.println("1. Drop passenger(s)");
            System.out.println("0. Go back");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            if (!input.matches("[01]")) {
                System.out.println("❌ Invalid input! Enter only 0 or 1.");
                continue;
            }

            int choice = Integer.parseInt(input);

            // BACK TO MAP
            if (choice == 0) {
                return false;
            }

            // DROP PASSENGERS
            System.out.print("Enter Passenger IDs to drop (space-separated): ");
            String[] parts = scanner.nextLine().split(" ");

            List<Integer> dropIDs = new ArrayList<>();

            for (String part : parts) {
                try {
                    dropIDs.add(Integer.parseInt(part));
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input ignored: " + part);
                }
            }

            if (dropIDs.isEmpty()) {
                System.out.println("❌ No valid Passenger IDs entered!");
                continue;
            }

            // PROCESS EACH PASSENGER TO DROP using new method
            boolean anyDropped = false;
            for (int pid : dropIDs) {
                // Check if passenger exists before trying to drop
                boolean passengerExists = passengers.stream().anyMatch(p -> p.id == pid);
                if (passengerExists) {
                    dropPassengerById(pid, currentStop);
                    anyDropped = true;
                } else {
                    System.out.println("❌ Passenger ID " + pid + " not found!");
                }
            }

            if (anyDropped) {
                // Display updated indicator after dropping passengers
                displayPassengerIndicator();

                // If all passengers are gone, break out of the loop
                if (passengers.isEmpty()) {
                    System.out.println("\nAll passengers have been dropped!");
                    break;
                }
            }
        }

        return true;
    }

    // Calculate fare and add to money (returns the amount earned)
    private int calculateAndAddFare(Passenger passenger, int currentStop) {
        int earnedFare;

        // CORRECT STOP
        if (passenger.dropStop == currentStop) {
            System.out.println("✔ Correct stop! Added full fare: ₱" + passenger.fare);
            earnedFare = passenger.fare;
        }
        // MISSED STOP(S)
        else {
            int missedStops = currentStop - passenger.dropStop;
            int totalReduction = missedStops * 2;
            earnedFare = Math.max(0, passenger.fare - totalReduction);

            System.out.println("\n❌ Missed Stop!");
            System.out.println("─────────────────────────────");
            System.out.println("• Missed Stops : " + missedStops);
            System.out.println("• Penalty      : -₱" + totalReduction);
            System.out.println("• Final Fare   : ₱" + earnedFare);
            System.out.println("─────────────────────────────");
            System.out.println("💰 Added to money: ₱" + earnedFare);
        }

        // Add money to the Map1 instance
        //gameMap.money += earnedFare;
        if (gameMap instanceof Map1 map1) {
            map1.money += earnedFare;
        } else if (gameMap instanceof Map2 map2) {
            map2.money += earnedFare;
        } else if (gameMap instanceof Map3 map3) {
            map3.money += earnedFare;
        } else {
            // Fallback - you might want to handle this differently
            System.out.println("❌ Error: Could not add money to map!");
        }
        return earnedFare;
    }

    // Generate random passengers for pickup
    public List<Passenger> generatePassengersForPickup(int currentStop, int totalStops, Random rand) {
        List<Passenger> newPassengers = new ArrayList<>();

        int countChance = rand.nextInt(4);
        if (countChance == 0) {
            return newPassengers; // No passengers
        }

        for (int i = 0; i < countChance; i++) {
            if (isFull()) {
                break;
            }

            int maxRange = totalStops - currentStop;
            int dropStop = currentStop + 1 + rand.nextInt(Math.max(1, maxRange));

            int t = rand.nextInt(3);
            String type;
            int discountMultiplier;

            if (t == 0) {
                type = "Student";
                discountMultiplier = 90;
            } else if (t == 1) {
                type = "Senior";
                discountMultiplier = 80;
            } else {
                type = "Worker";
                discountMultiplier = 100;
            }

            int baseFare = 5;
            int perStop = 2;

            int distance = dropStop - currentStop;
            int rawFare = baseFare + (distance * perStop);
            int finalFare = (rawFare * discountMultiplier) / 100;

            Passenger newPassenger = new Passenger(
                    passengerIdCounter,
                    type,
                    dropStop,
                    finalFare
            );

            newPassengers.add(newPassenger);
            passengerIdCounter++;
        }

        return newPassengers;
    }


}