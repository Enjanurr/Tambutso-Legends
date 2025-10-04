package Characters;

import java.util.Random;

public class Johnru extends Driver {
    Random rand = new Random();

    public Johnru() {
        super("Johnru", 8, 6, 3, 3, "Passenger Whisperer");
    }

    public int skill1() {
        System.out.println(name + " used Smooth Ride!");
        return rand.nextInt(6) + 5;  // 5–10
    }

    public int skill2() {
        System.out.println(name + " used Engine Tune!");
        return rand.nextInt(11) + 10; // 10–20
    }

    public int skill3() {
        System.out.println(name + " used Passenger Whisperer!");
        return rand.nextInt(16) + 8;  // 8–23
    }
}
