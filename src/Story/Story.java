package Story;
import Utils.Display;


public class Story {
    // helper method to simulate animation
    public static void outro() {
        Display display = new Display();
        System.out.println("══════════════════════════════════════════");
        System.out.println("        HARI NG KALSADA: THE LEGACY       ");
        System.out.println("══════════════════════════════════════════");
        System.out.println();

        display.printWithDelay("Sir Khai has fallen.", 50);
        display.sleep(1000);
        display.printWithDelay("The final stop has been conquered.", 50);
        display.sleep(1000);
        display.printWithDelay("But this journey was never just about speed.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("You braved the chaos of Cebu’s roads,", 50);
        display.printWithDelay("faced bullies, racers, and kings.", 50);
        display.sleep(1500);
        display.printWithDelay("You chose when to fight, when to help,", 50);
        display.printWithDelay("and when to pay the price of Gaba.", 50);
        display.sleep(2000);
        System.out.println();

        display.printWithDelay("⚡ Kharl, 🛡 Johnru, 🔥 James...", 50);
        display.printWithDelay("Each one carved a path through asphalt and fate.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("Now, the crown is yours.", 50);
        display.sleep(1000);
        display.printWithDelay("Not just as a driver...", 50);
        display.sleep(1000);
        display.printWithDelay("but as a legend.", 75);
        display.sleep(2000);
        System.out.println();

        display.printWithDelay("The streets remember your name.", 50);
        display.sleep(1000);
        display.printWithDelay("The passengers speak of your kindness.", 50);
        display.sleep(1000);
        display.printWithDelay("And the road bows to your legacy.", 50);
        display.sleep(2000);
        System.out.println();

        display.printWithDelay("You are now...", 75);
        display.sleep(1000);
        display.printWithDelay("✨ HARI NG KALSADA ✨", 100);
        display.sleep(2500);
        System.out.println();

        display.printWithDelay("══════════════════════════════════════════", 30);
        display.printWithDelay("        THANK YOU FOR PLAYING!            ", 50);
        display.printWithDelay("══════════════════════════════════════════", 30);
        display.sleep(1000);
    }

    public static void intro() {
        Display display = new Display();
        System.out.println("══════════════════════════════════════════");
        System.out.println("        HARI NG KALSADA: THE JOURNEY      ");
        System.out.println("══════════════════════════════════════════");
        System.out.println();

        display.printWithDelay("In the busy streets of Cebu,", 50);
        display.sleep(1000);
        display.printWithDelay("the road is more than just concrete.", 50);
        display.sleep(1000);
        display.printWithDelay("It is a battlefield of pride, survival, and destiny.", 50);
        display.sleep(1500);

        System.out.println();

        display.printWithDelay("Three young men step forward to chase the crown", 50);
        display.sleep(1000);
        display.printWithDelay("of 'Hari ng Kalsada'—the King of the Road.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("⚡ Kharl, the Veteran Driver – a son carrying his", 50);
        display.printWithDelay("   father’s broken legacy, fueled by speed and charm.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("🛡 Johnru, the Strategist – escaping burnout,", 50);
        display.printWithDelay("   he seeks peace, believing respect brings blessings.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("🔥 James, the Risk-Taker – daring and reckless,", 50);
        display.printWithDelay("   he lives for the thrill of danger and competition.", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("But the streets are unforgiving...", 50);
        display.sleep(500);
        display.printWithDelay("Rain makes roads slippery, the sun can break engines,", 50);
        display.printWithDelay("and every passenger, cop, or rival driver —", 50);
        display.sleep(2000);
        display.printWithDelay("Can decide your fate.", 75);
        display.sleep(2500);
        System.out.println();

        display.printWithDelay("Every choice carries weight, for Gaba—karma—", 50);
        display.printWithDelay("rewards kindness and punishes selfishness.", 50);
        display.sleep(2500);
        System.out.println();

        display.printWithDelay("Will you rise to claim respect, wealth, and legacy...", 50);
        display.sleep(1500);
        display.printWithDelay("or be forgotten in the chaos of the road?..", 50);
        display.sleep(1500);
        System.out.println();

        display.printWithDelay("Your journey begins now.", 50);
        display.printWithDelay("══════════════════════════════════════════", 30);
        display.sleep(1000);
    }
}
