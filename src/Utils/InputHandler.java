package Utils;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static int getChoice(String prompt, int min, int max) {
        int choice;
        do {
            choice = getInt(prompt);
            //Made changes for back to menu
            if (choice == 0) {// if 0 will go back menu
                return 0;
            }
            if (choice < min || choice > max) {
                System.out.println("❌ Invalid choice. Enter a number between " + min + " and " + max + ".");
            }
        } while (choice < min || choice > max);
        return choice;
    }
}