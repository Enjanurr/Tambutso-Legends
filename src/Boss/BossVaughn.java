package Boss;

import java.util.Random;

public class BossVaughn extends Bossing {
    Random rand = new Random();

    public BossVaughn() {
        super("Vaughn", 100); // lowered from 100 for balance
    }

    // Skill 1: Bumper Slam
    @Override
    public int attackSkill() {
        System.out.println(name + " uses Bumper Slam!");
        return rand.nextInt(6) + 25;
    }

    // Ultimate: Full Stop
    @Override
    public int ultimate() {
        System.out.println(name + " uses Full Stop!");
        return rand.nextInt(6) + 40; // 35-40 raw damage
    }
}
