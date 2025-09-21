package Story;

public class Intro {
    // helper method to simulate animation
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

    public static void show() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("        HARI NG KALSADA: THE JOURNEY      ");
        System.out.println("══════════════════════════════════════════");
        System.out.println();

        printWithDelay("In the busy streets of Cebu, the road is more", 30);
        printWithDelay("than just concrete—it is a battlefield of pride,", 30);
        printWithDelay("survival, and destiny.", 30);
        System.out.println();

        printWithDelay("Three young men step forward to chase the crown", 30);
        printWithDelay("of 'Hari ng Kalsada'—the King of the Road.", 30);
        System.out.println();

        printWithDelay("⚡ Kharl, the Hustler – a son carrying his", 30);
        printWithDelay("   father’s broken legacy, fueled by speed and charm.", 30);
        System.out.println();

        printWithDelay("🛡 Johnru, the Patience Master – escaping burnout,", 30);
        printWithDelay("   he seeks peace, believing respect brings blessings.", 30);
        System.out.println();

        printWithDelay("🔥 James, the Risk-Taker – daring and reckless,", 30);
        printWithDelay("   he lives for the thrill of danger and competition.", 30);
        System.out.println();

        printWithDelay("But the streets are unforgiving...", 50);
        printWithDelay("Rain makes roads slippery, the sun can break engines,", 30);
        printWithDelay("and every passenger, cop, or rival driver can decide", 30);
        printWithDelay("your fate.", 30);
        System.out.println();

        printWithDelay("Every choice carries weight, for Gaba—karma—", 30);
        printWithDelay("rewards kindness and punishes selfishness.", 30);
        System.out.println();

        printWithDelay("Will you rise to claim respect, wealth, and legacy...", 40);
        printWithDelay("or be forgotten in the chaos of the road?", 40);
        System.out.println();

        printWithDelay("Your journey begins now.", 60);
        System.out.println("══════════════════════════════════════════");
    }
}
