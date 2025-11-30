package Maps;

import Boss.*;
import Characters.Driver;
import Utils.InputHandler;
import java.util.*;
import main.Main;


public class Finale extends World {
    Bossing boss = new JolliKhai();

    public Finale() {
        super(0, 0);
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;

        while (!missionComplete) {

            int money = driver.getMoney();
            driver.baseFuel = 300;
            // ====================== SHOP ======================
            System.out.println("\n🚏 Finalleee");
            System.out.println("Defeat JolliKhai\n");

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("\n🎁 SHOP TIME!");
            boolean buying = true;
            while (buying) {
                System.out.println("\nYour money: ₱" + money);
                System.out.println("1. Buy Item");
                System.out.println("2. Resell Item");
                System.out.println("3. Exit Shop (Continue to Boss)");
                System.out.println("------------------------");
                System.out.println("0. Back to Main Menu");

                int mainChoice = InputHandler.getChoice("Choose: ", 0, 3);

                switch (mainChoice) {
                    case 0 -> {
                        int choice = InputHandler.getChoice("Do you want to go back to Main Menu? (1 - Yes, 2 - No): ", 1, 2);
                        if (choice == 1) {
                            System.out.println("\n🔙 Returning to Main Menu...");
                            main.Main.main(null);
                            return false;
                        } else {
                            continue;
                        }
                    }
                    case 1 -> {
                        System.out.println("\n🛒 WHAT DO YOU WANT TO BUY?");
                        System.out.println("1. RePhil (+50 Fuel) - ₱20");
                        System.out.println("2. Burning Tire (+40 dmg) - ₱20");
                        System.out.println("3. Bumper Shield (Block 40 dmg) - ₱20");
                        System.out.println("4. Back");
                        int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);

                        if (itemChoice == 4) continue;

                        if (money < 20) {
                            System.out.println("\n💸 You don't have enough money to buy another item.");
                            continue;
                        }

                        String selectedItem = switch (itemChoice) {
                            case 1 -> "RePhil";
                            case 2 -> "Burning Tire";
                            case 3 -> "Bumper Shield";
                            default -> "";
                        };

                        //===========Define item limits===========
                        int itemLimit = switch (selectedItem) {
                            case "RePhil" -> 3;
                            case "Burning Tire" -> 5;
                            case "Bumper Shield" -> 3;
                            default -> 5; // fallback safety
                        };

                        int currentQty = driver.inventory.getOrDefault(selectedItem, 0);

                        // ✅ Checks item limit
                        if (currentQty >= itemLimit) {
                            System.out.println("\n⚠️ You already have the maximum amount of " + selectedItem + " (" + itemLimit + "x)!");
                            continue;
                        }

                        // ✅ Deduct money and add item
                        money -= 20;
                        driver.buyItem(selectedItem);
                        System.out.println("\n✅ You bought 1x " + selectedItem + "! (" + driver.inventory.get(selectedItem) + "x total)");
                    }

                    case 2 -> {
                        if (driver.inventory.isEmpty()) {
                            System.out.println("\n❌ You have nothing to sell!");
                            continue;
                        }

                        System.out.println("\n💰 WHAT DO YOU WANT TO SELL?");
                        int i = 1;
                        List<String> items = new ArrayList<>(driver.inventory.keySet());
                        for (String item : items) {
                            System.out.println(i + ". " + item + " (x" + driver.inventory.get(item) + ")");
                            i++;
                        }
                        System.out.println(i + ". Back");

                        int sellChoice = InputHandler.getChoice("Choose: ", 1, i);
                        if (sellChoice == i) continue;

                        String itemToSell = items.get(sellChoice - 1);
                        int quantity = driver.inventory.get(itemToSell);

                        int resellPrice = 10;

                        driver.inventory.put(itemToSell, quantity - 1);
                        if (driver.inventory.get(itemToSell) <= 0) {
                            driver.inventory.remove(itemToSell);
                        }

                        money += resellPrice;
                        System.out.println("\n💵 You sold 1x " + itemToSell + " for ₱" + resellPrice + "!");
                    }

                    case 3 -> {
                        System.out.println("👋 Leaving shop...");
                        buying = false;
                    }
                }

                System.out.println("💰 Remaining Money: ₱" + money);

                System.out.println("\n🎒 Current Inventory:");
                if (driver.inventory.isEmpty()) {
                    System.out.println("❌ Your inventory is empty!");
                } else {
                    for (Map.Entry<String, Integer> entry : driver.inventory.entrySet()) {
                        System.out.println("• " + entry.getKey() + " (x" + entry.getValue() + ")");
                    }
                }
            }
            // ====================== BOSS FIGHT ======================
            BossFight bossFight = new BossFight(boss, driver, this);
            int result = bossFight.start();

            if (result == -1) {
                // Restart the entire map
                resetMap(driver, boss);
                return play(driver); // Recursively call play to restart the map
            }
            // ====================== VICTORY CHECK ======================
            if (result == 1) {
                System.out.println("✅ You defeated " + boss.name + "!");
                System.out.println("🎉 Mission Success! Finale Map Complete!");
                System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                missionComplete = true;
                return true;
            }
        }
        return false;
    }

/*
    private boolean retryPrompt(Driver driver, Bossing boss) {
        int choice = InputHandler.getChoice("\n🔁 Try again Map Finale? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = (choice == 1);

        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map Finale");
        } else {
            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }
    */
    private void resetMap(Driver driver, Bossing boss) {
        driver.baseFuel = 300;
        driver.inventory.clear();
        boss.fuel = 450;

    }


}
