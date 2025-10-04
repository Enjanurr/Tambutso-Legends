import Story.Intro;
import Maps.Map1;
import Characters.*;
import Utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        // Display intro (optional)
        // Intro.show();

        // 🔹 Loop until user presses 1
        int play = 0;
        while (play != 1) {
            play = InputHandler.getInt("Press 1 to start: ");
            if (play != 1) {
                System.out.println("❌ Invalid input. Please press 1 to continue.");
            }
        }

        Driver driver = null;

        // 🔹 Keep asking until valid character choice
        while (driver == null) {
            int choice = InputHandler.getInt("Enter your choice (1=Kharl, 2=Johnru, 3=James): ");

            switch (choice) {
                case 1 -> driver = new Kharl();
                case 2 -> driver = new Johnru();
                case 3 -> driver = new James();
                default -> System.out.println("❌ Invalid input. Please choose 1, 2, or 3.");
            }
        }

        System.out.println("✅ You chose: " + driver.name + " with skill: " + driver.uniqueSkill);

        // 🚀 Start Map 1
        new Map1().play(driver);
    }
}
