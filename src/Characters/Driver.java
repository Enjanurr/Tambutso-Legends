package Characters;

import java.util.Random;

public abstract class Driver {
    Random rand = new Random();
    public String name;

    public int speed;
    public int fuelEfficiency;  // how slowly fuel decreases
    public int skillChance;     // chance to use skill successfully
    public int riskLevel;       // chance of mistakes
    public String uniqueSkill;

    public int fuel = 100;      // starting fuel
    public int passengerCount = 0;
    public int maxPassengers = 20; // jeep capacity

    public Driver(String name, int speed, int fuelEfficiency, int skillChance, int riskLevel, String uniqueSkill) {
        this.name = name;
        this.speed = speed;
        this.fuelEfficiency = fuelEfficiency;
        this.skillChance = skillChance;
        this.riskLevel = riskLevel;
        this.uniqueSkill = uniqueSkill;
    }

    // ✅ passenger methods
    public boolean addPassenger() {
        if (passengerCount < maxPassengers) {
            passengerCount += rand.nextInt(2) + 3; // pick 3-4 passengers
            System.out.println(name + " picked up passengers. Total: " + passengerCount);
            return true;
        } else {
            System.out.println(name + "'s jeep is full!");
            return false;
        }
    }

    public boolean dropPassenger() {
        if (passengerCount > 0) {
            passengerCount -= rand.nextInt(2) + 3; // drop 3-4 passengers
            passengerCount = Math.max(0, passengerCount);
            System.out.println(name + " dropped passengers. Total: " + passengerCount);
            return true;
        } else {
            System.out.println("No passengers to drop.");
            return false;
        }
    }

    public void showPassengers() {
        System.out.println(name + " has " + passengerCount + " passengers on board.");
    }

    // Abstract skills
    public abstract int skill1();
    public abstract int skill2();
    public abstract int skill3();
}
