package Characters;

import java.util.Random;

public class James extends Driver {
    // added skill fields
    private final String skill1 = "Crazy Drift";
    private final String skill2 = "Wild Overdrive";
    private final String skill3 = "Final Gear";
    Random rand = new Random();

    // changed title
    public James() {
        super("James", 5, 9, 8, 4, "Risk-Taker");
    }

    // added overrides
    @Override
    public int skill1() {
        System.out.println(name + " used Crazy Drift!");
        return rand.nextInt(26) + 5;  // 25-30
    }

    @Override
    public int skill2() {
        System.out.println(name + " used Wild Overdrive!");
        return rand.nextInt(46) + 5; // 45-50
    }

    @Override
    public int skill3() {
        System.out.println(name + " used Final Gear!");
        return rand.nextInt(76) + 5; // 75-80
    }

    // added skill getters
    @Override
    public String getSkill1() { return skill1; }
    @Override
    public String getSkill2() { return skill2; }
    @Override
    public String getSkill3() { return skill3; }
}
