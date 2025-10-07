package BossMap;
import Characters.Driver;
import java.util.Random;
public class BossUtils {
    private static final Random rand = new Random();

    public static int getRandom(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static int getDriverDefenseSafe(Driver driver) {

        int baseDefense = 10; // example base defense value
        return baseDefense;
    }
}