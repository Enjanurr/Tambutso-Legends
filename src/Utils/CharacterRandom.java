package Utils;

import Characters.Driver;
import java.util.Random;

public class CharacterRandom {
    private static Random rand = new Random();

    public static boolean randomEvent(int chancePercent) {
        return rand.nextInt(100) < chancePercent;
    }

    public static int randomFuelLoss(int base, Driver driver) {
        return rand.nextInt(base) + (10 - driver.fuelEfficiency);
    }
}
