package Characters;

import java.util.Random;

public class Johnru extends Driver {
    Random rand = new Random();

    public Johnru() {
        super("Johnru", 8, 6, 3, 3, "Passenger Whisperer");
    }

    public int skill1() {
        System.out.println(name + " used Smooth Maneuver!");
        return rand.nextInt(16) + 5;  // 15–20
    }

    public int skill2() {
        System.out.println(name + " used Fare Tactician!");
        return rand.nextInt(36) + 5; // 35-40
    }

    public int skill3() {
        System.out.println(name + " used Perfect Route!");
        return rand.nextInt(61) + 5;  // 60-65
    }
}
