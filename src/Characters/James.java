package Characters;

import java.util.Random;

public class James extends Driver {
    Random rand = new Random();

    public James() {
        super("James", 5, 9, 8, 4, "Wild Overtake");
    }

    public int skill1() {
        System.out.println(name + " used Crazy Drift!");
        return rand.nextInt(26) + 5;  // 25-30
    }

    public int skill2() {
        System.out.println(name + " used Wild Overdrive!");
        return rand.nextInt(46) + 5; // 45-50
    }

    public int skill3() {
        System.out.println(name + " used Final Gear!");
        return rand.nextInt(76) + 5; // 75-80
    }
}
