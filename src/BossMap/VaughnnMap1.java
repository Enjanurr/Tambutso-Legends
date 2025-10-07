package BossMap;

import Characters.Driver;
import java.util.Random;

public class VaughnnMap1 extends Boss {
    private int bumperSlamCooldown = 0; // turns remaining until available
    private int fullStopCooldown = 0;
    private Random rand = new Random();

    public VaughnnMap1() {
        super("Vaughn, the Road Bully", 150, 15);
    }
    @Override
    public ActionResult performAction(Driver driver) {
        // decrease cooldowns at start of boss turn
        if (bumperSlamCooldown > 0) bumperSlamCooldown--;
        if (fullStopCooldown > 0) fullStopCooldown--;

        //use ultimate when available with some randomness (25% chance)
        boolean useUltimate = false;
        if (fullStopCooldown == 0) {
            useUltimate = rand.nextInt(100) < 25; // 25% chance to use ultimate when off cooldown
        }

        ActionResult result = new ActionResult();

        if (useUltimate) {
            // Full Stop
            int base = 40 + (rand.nextInt(6) + 5);
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int dmg = Math.max(0, base - playerDef);
            result.damageToPlayer = dmg;
            result.stunnedPlayer = true;
            fullStopCooldown = 4; // set cooldown
            System.out.println(name + " used FULL STOP! It deals " + dmg + " damage and STUNS you for 1 turn!");
        } else {
            // use bumper slam if available, otherwise try ultimate if forced
            if (bumperSlamCooldown == 0) {
                int base = 25 + rand.nextInt(6); // 25 + R(0-5)
                int playerDef = BossUtils.getDriverDefenseSafe(driver);
                int dmg = Math.max(0, base - playerDef);
                result.damageToPlayer = dmg;
                bumperSlamCooldown = 2;
                System.out.println(name + " used BUMPER SLAM! It deals " + dmg + " damage.");
            } else {
                // if bumper slam on cd, do a weaker auto-attack (scaled)
                int base = 18 + rand.nextInt(4); // smaller attack
                int playerDef = BossUtils.getDriverDefenseSafe(driver);
                int dmg = Math.max(0, base - playerDef);
                result.damageToPlayer = dmg;
                System.out.println(name + " rams forward angrily, dealing " + dmg + " damage.");
            }
        }
        return result;
    }
}
