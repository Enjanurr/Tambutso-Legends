package Characters;

import java.util.Random;

public class Kharl extends Driver {
    Random rand = new Random();

    public Kharl() {
        super("Kharl", 6, 7, 5, 3, "Quick Shift");
    }

    public int skill1() {
        System.out.println(name + " used Quick Shift!");
        return rand.nextInt(11) + 12; // 12–22
    }

    public int skill2() {
        System.out.println(name + " used Turbo Slide!");
        return rand.nextInt(16) + 10; // 10–25
    }

    public int skill3() {
        System.out.println(name + " used Full Throttle!");
        return rand.nextInt(21) + 15; // 15–35
    }
}
