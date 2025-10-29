package Story;
import Utils.Display;


public class Intro {
    // helper method to simulate animation
   public void show() {
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
