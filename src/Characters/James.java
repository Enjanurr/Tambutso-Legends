package Characters;

import java.util.Random;

public class James extends Driver {
    Random rand = new Random();

    public James() {
        super("James", 5, 9, 8, 4, "Wild Overtake");
    }

    public int skill1() {
        System.out.println(name + " used Nitro Boost!");
        return rand.nextInt(21) + 5;  // 5–25
    }

    public int skill2() {
        System.out.println(name + " used Wild Overtake!");
        return rand.nextInt(16) + 15; // 15–30
    }

    public int skill3() {
        System.out.println(name + " used Risky Drift!");
        return rand.nextInt(26) + 10; // 10–35
    }
}
