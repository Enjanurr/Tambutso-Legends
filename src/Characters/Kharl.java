package Characters;

import java.util.Random;

public class Kharl extends Driver {
    // added skill fields
    private final String skill1 = "Turbo Start";
    private final String skill2 = "Nitro Spin";
    private final String skill3 = "Tambutso";
    Random rand = new Random();

    // changed title
    public Kharl() {
        super("Kharl", 6, 7, 5, 3, " Cocky Veteran");
    }

    // added overrides
    @Override
    public int skill1() {
        System.out.println(name + " used " + skill1 + "!");
        return rand.nextInt(21) + 5; // 20-15
    }

    @Override
    public int skill2() {
        System.out.println(name + " used " + skill2 + "!");
        return rand.nextInt(41) + 5; // 40-45
    }

    @Override
    public int skill3() {
        System.out.println(name + " used " + skill3 + "!");
        return rand.nextInt(71) + 5; // 70 - 75
    }

    // added skill getters
    @Override
    public String getSkill1() { return skill1; }

    @Override
    public String getSkill2() { return skill2; }

    @Override
    public String getSkill3() { return skill3; }
}
