package Maps;

import Characters.Driver;
import EnemyJeep.JeepMap1;
import Utils.InputHandler;
import java.util.Random;

public class Map1 extends World {
    private Random rand = new Random();

    public Map1() {
        super(5, 5); // gaba=5, stops=5
    }

    @Override
    public void play(Driver driver) {
        int fuel = driver.fuel;
        boolean contestWon = false;

        System.out.println("\n🚏 Starting Map 1: Naga to Minglanilla (" + stops + " stops)");
        System.out.println("Mission: Pick up 10 passengers, manage fuel, win 1 contest.\n");

        for (int stop = 1; stop <= stops; stop++) {
            System.out.println("\n--- Stop " + stop + " ---");
            System.out.println("Fuel: " + fuel + " | Passengers: " + passengers);
            System.out.println("Choose an action:");
            System.out.println("1. Pick up passengers");
            System.out.println("2. Skip stop (save fuel)");

            int action = InputHandler.getChoice("Your choice: ", 1, 2);

            if (action == 1) {
                int newPassengers = rand.nextInt(3) + 1;
                passengers += newPassengers;
                System.out.println("Picked up " + newPassengers + " passengers. Total: " + passengers);
            } else {
                System.out.println("You skipped this stop.");
            }

            // 🚨 Rival contest (30% chance)
            if (rand.nextInt(100) < 30) {
                JeepMap1 rival = new JeepMap1();
                rival.showStats();

                System.out.println("\n⚔️ " + rival.getName() + " challenges you for passengers!");
                System.out.println("1. Use a skill to fight");
                System.out.println("2. Avoid conflict (-1 passenger)");

                int battleChoice = InputHandler.getChoice("Your choice: ", 1, 2);

                if (battleChoice == 1) {
                    // Let player pick which skill to use
                    System.out.println("Choose a skill:");
                    System.out.println("1. Skill 1");
                    System.out.println("2. Skill 2");
                    System.out.println("3. Skill 3");

                    int skillChoice = InputHandler.getChoice("Skill: ", 1, 3);
                    int damage = switch (skillChoice) {
                        case 1 -> driver.skill1();
                        case 2 -> driver.skill2();
                        case 3 -> driver.skill3();
                        default -> 0;
                    };

                    boolean won = rival.compete(driver);

                    if (won) {
                        System.out.println("✅ " + driver.name + " dealt " + damage + " and WON the contest!");
                        passengers += 2;
                        contestWon = true;
                    } else {
                        System.out.println("❌ " + driver.name + " lost the contest and lost 1 passenger!");
                        passengers = Math.max(0, passengers - 1);
                    }
                } else {
                    System.out.println("😬 You avoided the contest. Lost 1 passenger.");// lost passenger must be randomize
                    passengers = Math.max(0, passengers - 1);
                }
            }

            // ⛽ Fuel consumption
            int fuelLoss = rand.nextInt(6) + (10 - driver.fuelEfficiency); //fuel lost must be higher
            fuel -= fuelLoss;
            driver.fuel = fuel;
            System.out.println("Fuel decreased by " + fuelLoss + ". Remaining fuel: " + fuel);

            if (fuel <= 0) {
                System.out.println("❌ You ran out of fuel! Game Over.");
                return;
            }
        }

        // ✅ Mission check
        System.out.println("\n--- Map 1 Complete! ---");
        System.out.println("Passengers carried: " + passengers);

        if (passengers >= 10 && contestWon) {
            System.out.println("🎉 Mission Success! Get ready for the next route.");
        } else {
            System.out.println("⚠️ Mission Incomplete. Try again to finish all goals.");
        }
    }
}
