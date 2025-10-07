package BossMap;
import java.util.Random;
import Characters.Driver;
public class SirKhaiMap3 extends Boss {
    private int precisionDriftCooldown = 0;
    private int overdriveCooldown = 0;
    private Random rand = new Random();

    public SirKhaiMap3() {
        super("Sir Khai, the King of the Roads", 300, 30);
    }
    @Override
    public ActionResult performAction(Driver driver) {
        // decrease cooldowns every turn
        if (precisionDriftCooldown > 0) precisionDriftCooldown--;
        if (overdriveCooldown > 0) overdriveCooldown--;
        ActionResult result = new ActionResult();
        boolean useUltimate = false;

        // 30% chance to use ultimate when it's ready
        if (overdriveCooldown == 0) {
            useUltimate = rand.nextInt(100) < 30;
        }
        if (useUltimate) {
            int base = 70 + (rand.nextInt(11) + 10); // 70 + random(10–20)
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int damage = Math.max(0, base - playerDef);
            result.damageToPlayer = damage;
            result.halveNextPlayerSkill = true; // apply debuff
            overdriveCooldown = 4;

            System.out.println(name + " used OVERDRIVE AUTHORITY! ⚡");
            System.out.println("It deals " + damage + " damage and halves your next skill’s damage!");
        } else if (precisionDriftCooldown == 0) {
            // ⚙️ Precision Drift
            int base = 35 + (rand.nextInt(6) + 10); // 35 + random(10–15)
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int damage = Math.max(0, base - playerDef);

            result.damageToPlayer = damage;
            precisionDriftCooldown = 2;

            System.out.println(name + " executed a PRECISION DRIFT! 🚗💨");
            System.out.println("It deals " + damage + " damage efficiently!");
        } else {
            // normal attack when skills are on cooldown
            int base = 20 + rand.nextInt(6); // weaker fallback
            int playerDef = BossUtils.getDriverDefenseSafe(driver);
            int damage = Math.max(0, base - playerDef);
            result.damageToPlayer = damage;
            System.out.println(name + " performs a light scrape, dealing " + damage + " damage.");
        }
        return result;
    }
}
