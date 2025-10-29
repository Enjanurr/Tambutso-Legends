package Characters;

import java.util.Random;

public class Johnru extends Driver {
    // added skill fields
    private final String skill1 = "Smooth Maneuver";
    private final String skill2 = "Fare Tactician";
    private final String skill3 = "Perfect Route";
    Random rand = new Random();

    // changed title
    public Johnru() {
        super("Johnru", 8, 6, 3, 3, "Strategist");
    }

    // added overrides
    @Override
    public int skill1() {
        System.out.println(name + " used Smooth Maneuver!");
        return rand.nextInt(16) + 5;  // 15–20
    }

    @Override
    public int skill2() {
        System.out.println(name + " used Fare Tactician!");
        return rand.nextInt(36) + 5; // 35-40
    }

    @Override
    public int skill3() {
        System.out.println(name + " used Perfect Route!");
        return rand.nextInt(61) + 5;  // 60-65
    }

    // added skill getters
    @Override
    public String getSkill1() { return skill1; }
    @Override
    public String getSkill2() { return skill2; }
    @Override
    public String getSkill3() { return skill3; }
}
