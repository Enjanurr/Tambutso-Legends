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


    //public int fuel = 100;      // starting fuel
    public int passengerCount = 0;
    public int maxPassengers = 20; // jeep capacity

    public Driver(String name, int speed, int fuelEfficiency,int skillChance, int riskLevel, String uniqueSkill) {
        this.name = name;
        this.speed = speed;
        this.fuelEfficiency = fuelEfficiency;

        this.skillChance = skillChance;
        this.riskLevel = riskLevel;
        this.uniqueSkill = uniqueSkill;


    }



    public void showPassengers() {
        System.out.println(name + " has " + passengerCount + " passengers on board.");
    }

    // Abstract skills
    public abstract int skill1();
    public abstract int skill2();
    public abstract int skill3();
}
