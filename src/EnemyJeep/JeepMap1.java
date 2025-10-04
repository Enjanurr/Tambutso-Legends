package EnemyJeep;

import Characters.Driver;
import java.util.Random;

public class JeepMap1 extends Enemy {
    Random rand = new Random();

    public JeepMap1() {
        super("Tutorial Jeepney", 5, "Basic Block");
    }

    @Override
    public boolean compete(Driver player) {
        int playerPower = player.speed + rand.nextInt(10);
        int enemyPower = this.strength + rand.nextInt(10);

        System.out.println("Player Power: " + playerPower + " | Enemy Power: " + enemyPower);

        return playerPower >= enemyPower;
    }
}
