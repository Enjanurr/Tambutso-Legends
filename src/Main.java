package main;

import Characters.*;
import Maps.*;
import Utils.*;
import Story.*;
import AsciiArts.*;

public class Main {
    public static void main(String[] args) {
        int play = 0;
        while (play != 1) {
            play = InputHandler.getInt("Press 1 to start: ");
        }

        // added option to skip story
        char skip = '0';
        while (skip != 'Y' && skip != 'N') {
            skip = Character.toUpperCase(InputHandler.getChar("Skip story? (Y/N): "));
        }
        if (skip == 'N') {
            Story.intro();
        }

        try {
            AsciiArt.printTitleArt1();
            System.out.println("\n");
            AsciiArt.printTitleArt2();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // cleaned up display
        Display display = new Display();
        display.printDrivers();

        Driver driver = null;
        while (driver == null) {
            int choice = InputHandler.getInt("Enter: ");
            switch (choice) {
                case 1 -> driver = new Kharl();
                case 2 -> driver = new Johnru();
                case 3 -> driver = new James();
                default -> System.out.println("❌ Invalid choice.");
            }
        }

        System.out.println("✅ You chose: " + driver.name + " (" + driver.uniqueSkill + ")");


        //Tutorials tuts = new Tutorials();
        //tuts.play(driver);

        driver.levelUp(1); Map1 map1 = new Map1(); boolean successMap1 = map1.play(driver);
        //boolean successMap1 = true; // debug purposes
        if (!successMap1) {
            System.out.println("\n💤 You didn’t finish Map 1. Try again next time!");
        } else {
            System.out.println("\n🚦 Proceeding to Map 2...");
            try {
                Thread.sleep(1000);
                AsciiArt.printSunset();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.levelUp(2);
            Map2 map2 = new Map2();
            boolean successMap2 = map2.play(driver);
            //boolean successMap2 = true; // debug purposes

            if (!successMap2) {
                System.out.println("\n💤 You didn’t finish Map 2. Try again next time!");
            } else {
                System.out.println("\n🚦 Proceeding to Map 3...");
                try {
                    Thread.sleep(1000);
                    AsciiArt.printSunset();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                driver.levelUp(3);
                Map3 map3 = new Map3();
                //1
                boolean successMap3 = map3.play(driver);
                //boolean successMap3 = true; // debug purposes

                if (!successMap3) {
                    System.out.println("\n💀 You didn’t defeat the boss in Map 3. Try again!");
                } else {
                    System.out.println("\n🏁 Proceeding to the Finale...");
                    try {
                        Thread.sleep(1000);
                        AsciiArt.printFinaleEncounter();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Finale finale = new Finale();
                    finale.play(driver);
                    Story.outro();
                    AsciiArt.printVictoryArt();
                }
            }
        }
    }
}
