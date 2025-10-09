package Shop;
import java.util.Random;

public class RandomItems {
    Random rand = new Random();

    public RandomItems(){

    }

    // Refill fuel: restores 10–25
    public int rePhill(){
        int fuelGain = rand.nextInt(16) + 10; // 10–25
        System.out.println("You refill your tank and restored " + fuelGain + " fuel.");
        return fuelGain;
    }

    // Attack item: Burning Tire, 15–30 damage
    public int burningTire(){
        int damage = rand.nextInt(16) + 35; // 15–30
        System.out.println("You used Burning Tire and dealt " + damage + " damage to the boss.");
        return damage;
    }

    // Shield item: Bumper Shield, blocks 10–20 damage
    public int bumperShield(){
        int block = rand.nextInt(11) + 10; // 10–20
        System.out.println("You use Bumper Shield and blocked " + block + " damage from the boss.");
        return block;
    }
}
