package Characters;

import java.util.Random;

public class Kharl extends Driver {
    Random rand = new Random();

    public Kharl() {
        super("Kharl", 6, 7, 5, 3, "Quick Shift");
    }

    public int skill1() {
        System.out.println(name + " used Turbo Start!");
        return rand.nextInt(21) + 5; // 20-15
    }

    public int skill2() {
        System.out.println(name + " used Nitro Spin!");
        return rand.nextInt(41) + 5; // 40-45
    }

    public int skill3() {
        System.out.println(name + " used Tambutso Burst!");
        return rand.nextInt(71) + 5; // 70 - 75
    }
}
