package Maps;

import Characters.Driver;
import Utils.InputHandler;
import java.util.Random;

public class Tutorials extends World {

    private Random rand = new Random();

    public Tutorials() {
        super(0, 3); // tutorial has 3 stops only
    }

    // helper for narration with typing effect
    private static void printWithDelay(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay); // delay per character
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); // move to next line after full text
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;
        int fuel = 100;       // tutorial fuel (fixed for simplicity)
        int passengers = 0;   // tutorial passengers

        printWithDelay("🚦 Welcome to Tutorial Mode!", 30);
        printWithDelay("In this tutorial, we’ll teach you the basics of being a jeepney driver.", 30);
        printWithDelay("Your goal is to pick up passengers, manage fuel, and survive rival jeeps.", 30);

        while (!missionComplete) {
            for (int stop = 1; stop <= stops; stop++) {
                printWithDelay("\n--- Tutorial Stop " + stop + " ---", 20);
                System.out.println("Fuel: " + fuel + " | Passengers: " + passengers);

                int action;

                // Teaching steps
                if (stop == 1) {
                    printWithDelay("This is Stop 1. Let’s practice picking up passengers.", 30);
                    action = InputHandler.getChoice("Press 1 to Pick up passengers: ", 1, 1); // force pick
                } else if (stop == 2) {
                    printWithDelay("Good job! Now let’s try skipping a stop to save some fuel.", 30);
                    action = InputHandler.getChoice("Press 2 to Skip stop: ", 2, 2); // force skip
                } else {
                    printWithDelay("Now you can choose freely. Pick passengers or skip.", 30);
                    action = InputHandler.getChoice("Your choice (1 = Pick, 2 = Skip): ", 1, 2);
                }

                // Action results
                if (action == 1) {
                    int newPassengers = rand.nextInt(3) + 1; // 1–3 passengers
                    passengers += newPassengers;

                    int fuelLoss = rand.nextInt(4) + 6; // moderate fuel loss
                    fuel -= fuelLoss;

                    printWithDelay("You picked up " + newPassengers + " passengers.", 25);
                    printWithDelay("Fuel decreased by " + fuelLoss + ". Remaining fuel: " + fuel, 25);
                } else {
                    int fuelLoss = rand.nextInt(2) + 3; // smaller loss when skipping
                    fuel -= fuelLoss;
                    printWithDelay("You skipped this stop to save fuel.", 25);
                    printWithDelay("Fuel decreased by " + fuelLoss + ". Remaining fuel: " + fuel, 25);
                }

                // Rival jeep event at last stop
                if (stop == 3) {
                    int stolen = rand.nextInt(2) + 1;
                    passengers = Math.max(0, passengers - stolen);
                    printWithDelay("⚠️ A rival jeep appeared and stole " + stolen + " of your passengers!", 25);
                }
            }

            // End of tutorial
            printWithDelay("\n✅ Tutorial complete!", 30);
            printWithDelay("You learned how to:", 30);
            printWithDelay("👉 Pick up passengers", 30);
            printWithDelay("👉 Skip to save fuel", 30);
            printWithDelay("👉 Deal with rival jeeps", 30);
            printWithDelay("Now you are ready for the real routes!", 30);

            missionComplete = true; // exit loop
        }
        return true;
    }
}
