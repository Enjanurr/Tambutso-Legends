package BossMap;

import Characters.Driver;
import java.util.Random;

public class AdrianMap2 extends Boss {
    private int engineRushCooldown = 0;
    private int nitroRageCooldown = 0;
    private Random rand = new Random();

    public AdrianMap2(){
        super("Adrian, the Reckless Racer", 200, 20);
    }
    @Override
    public ActionResult performAction(Driver driver){
        if(engineRushCooldown > 0) engineRushCooldown--;
        if(nitroRageCooldown >  0) nitroRageCooldown--;

        ActionResult result = new ActionResult();
        if(nitroRageCooldown == 0 && rand.nextInt(100) < 25){
            int base = 50 + (rand.nextInt(6) + 10);
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int damage = Math.max(0, base - playerDef);
            result.damageToPlayer = damage;
            result.accuracyDebuffTurns = 2;
            nitroRageCooldown = 4;
            System.out.println(name + " unleashed NITRO RAGE! It deals " + damage + " damage and lowers your accuracy for 2 turns! ");
            return result;
        }
        if(engineRushCooldown == 0){
            int base = 30 + (rand.nextInt(6) + 5);
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int damage = Math.max(0, base - playerDef);
            result.damageToPlayer = damage;
            engineRushCooldown = 2;
            System.out.println(name + " use ENGINE RUSH! It deals " + damage + " damage.");
            return result;
        }
        int base = 22 + rand.nextInt(6);
        int playerDef = BossUtils.getDriverDefenseSafe(driver);
        int damage = Math.max(0, base - playerDef);
        result.damageToPlayer = damage;
        System.out.println(name + " charges the road and deals" + damage + " damage.");
        return result;
    }
}
