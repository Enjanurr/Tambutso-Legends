package Maps;

import Characters.Driver;
import java.util.Random;

public abstract class World {
    protected int gaba;        // difficulty
    protected int passengers;  // shared passenger count
    protected int stops;       // how many stops per map
    protected Random rand = new Random();

    public World(int gaba, int stops) {
        this.gaba = gaba;
        this.stops = stops;
        this.passengers = 0;
    }

    public abstract void play(Driver driver);
}
