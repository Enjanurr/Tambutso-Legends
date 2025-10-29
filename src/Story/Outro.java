package Story;
import Utils.Display;

public class Outro {
    public void show() {
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
}