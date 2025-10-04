package EnemyJeep;

import Characters.Driver;
import java.util.Random;

public abstract class Enemy {
    protected String name;
    protected int strength;
    public int fuel = 100;
    protected String uniqueSkill;

    protected Random rand = new Random();

    public Enemy(String name, int strength, String uniqueSkill) {
        this.name = name;
        this.strength = strength;
        this.uniqueSkill = uniqueSkill;
    }

    // Getters
    public String getName() { return name; }
    public int getStrength() { return strength; }
    public String getUniqueSkill() { return uniqueSkill; }

    // Show stats
    public void showStats() {
        System.out.println("🚐 Rival: " + name);
        System.out.println("   Strength: " + strength + "/10");
        System.out.println("   Unique Skill: " + uniqueSkill);
    }

    public abstract boolean compete(Driver player);
}
