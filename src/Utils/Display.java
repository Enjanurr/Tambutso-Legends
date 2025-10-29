package Utils;

import Characters.Descriptions;

public class Display {
    public void printWithDelay(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(delay);
        }
        System.out.println(); // move to next line after full text
    }

    public void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printDrivers() {
        Descriptions desc = new Descriptions();
        System.out.println("\nChoose your Driver:");
        printWithDelay(desc.kharlDesc(), 15);
        sleep(1000);
        printWithDelay(desc.johnruDesc(),15);
        sleep(1000);
        printWithDelay(desc.jamesDesc(), 15);
        sleep(1000);
    }
}

