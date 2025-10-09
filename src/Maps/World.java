package Maps;

import Characters.Driver;
import java.util.Random;

public abstract class World {
    public int gaba;        // difficulty
    public int passengers;  // shared passenger count
    public int stops;       // how many stops per map

    public int money;

    protected Random rand = new Random();

    public World(int gaba, int stops) {
        this.gaba = gaba;
        this.stops = stops;
        this.passengers = 0;
        this.money = 0;
    }

    public abstract boolean play(Driver driver);
}
