package Maps;

import Boss.*;
import Characters.Driver;
import Utils.InputHandler;
import java.util.*;
import main.Main;

public class Map1 extends World {
    Bossing boss = new BossVaughn();
    public int money;
    public  int passengers;
    int stop = 0;
    private Random rand = new Random();
    private PassengerList passengerList;
    private Driver driver;


    public Map1() {
        super(30, 10);
        passengerList = new PassengerList(driver, this);
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;
        String[] destination = new String[]{
                "Naga City Jeepney Terminal",
                "KEPCO", "Inoburan Stop ",
                "Tinaan Crossing",
                "Langtad Stop",
                "Cantao-an Junction",
                "Tunghaan Stop (Boundary Area)",
                "Calajo-an Stop", "Tulic Stop",
                "Minglanilla Public Market / Town Proper"
        };
        while (!missionComplete) {
            driver.baseFuel = 150;
            passengers = 0;
            money = 0;
            boolean failedRun = false;

            System.out.println("\n🚏 Starting Map 1: Naga to Minglanilla (" + stops + " stops)");
            System.out.println("Mission: Earn ₱70+ from 10 stops and defeat Boss Vaughn.\n");
            for (stop = 1; stop <= stops; stop++) {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("=== Map 1: Naga to Minglanilla ===");
                System.out.println("Stop " + stop + " | Destination: " + destination[stop - 1]);
                System.out.println("------------------------");
                System.out.println("Fuel: " + driver.baseFuel + " | Passengers: " + passengerList.getPassengerCount() + " | Money: ₱" + money);
                //NEW DISPLAY==========
                passengerList.displayPassengerIndicator();
                System.out.println("1. Pick up passengers");
                System.out.println("2. Skip stop");
                System.out.println("3. View passenger list");
                System.out.println("0. Back to Main Menu");

                int action = InputHandler.getChoice("Your choice: ", 0, 3);

                // ================ RETURN TO MENU ================
                if (action == 0) {
                    if (InputHandler.getChoice("Back to main menu? (1-Yes, 2-No): ", 1, 2) == 1) {
                        Main.main(null);
                        return false;
                    }
                    stop--;
                    continue;
                }
                // ================ ACTION: PICKUP PASSENGERS ================
                if (action == 1) {
                    if (passengerList.isFull()) {
                        System.out.println("\n⚠ Jeepney full (13/13). Drop passengers first!");
                        stop--;
                        continue;
                    }

                    Random rand = new Random();
                    int fuelLoss = rand.nextInt(3) + 8;
                    driver.baseFuel -= fuelLoss;

                    if (driver.baseFuel <= 0) {
                        System.out.println("❌ You ran out of fuel! Game Over.");
                        failedRun = true;
                        break;
                    }

                    // Generate new passengers for pickup
                    List<PassengerList.Passenger> newPassengers = passengerList.generatePassengersForPickup(stop, stops, rand);

                    if (newPassengers.isEmpty()) {
                        System.out.println("\nNo passengers at this stop.");
                        System.out.println("Fuel Used: " + fuelLoss + " | Remaining Fuel: " + driver.baseFuel);

                        continue;
                    }
                    System.out.println("\n🚖 Passenger Pickup");
                    System.out.println("------------------------");
                    System.out.println("Picked Up: " + newPassengers.size() + " passenger(s)");
                    System.out.println("Fuel Used: " + fuelLoss + " | Remaining: " + driver.baseFuel);

                    // Add each generated passenger to the passenger list
                    for (PassengerList.Passenger passenger : newPassengers) {
                        passengerList.addPassenger(passenger);
                        System.out.println(
                                "Passenger " + passenger.id +
                                        " (" + passenger.type + ") | Drop: Stop " + passenger.dropStop +
                                        " | Fare: ₱" + passenger.fare
                        );
                        passengers++;
                    }

                    System.out.println("🚘 Passengers Onboard: " + passengerList.getPassengerCount());

                }

                // ================ ACTION: SKIP STOP ================
                else if (action == 2) {
                    int fuelLoss = rand.nextInt(2) + 5;
                    driver.baseFuel -= fuelLoss;
                    System.out.println("You skipped this stop (Fuel -" + fuelLoss + ")");

                }


                // ================ ACTION: VIEW PASSENGERS ================
                else if (action == 3) {
                    if (!passengerList.showPassengerList(stop)) {
                        stop--;
                    }

                    continue;
                }

                // ================ RANDOM EVENTS ("GABA") ================
                if (rand.nextInt(100) < gaba) {

                    if (money <= 0) {
                        System.out.println("\n💰 No money → Gaba skipped.");
                        continue;
                    }

                    int randomGaba = rand.nextInt(5) + 1;
                    // ====================== GABA EVENT ======================
                    System.out.println("\n===============================");
                    System.out.println(" ⚠ RANDOM EVENT OCCURRED! ");
                    System.out.println("===============================");
                    System.out.println("Event: " + randomGaba);

                    switch (randomGaba) {
                        case 1: // Flat Tire
                            System.out.println("🚗 Flat Tire! -5 Fuel or Pay ₱3");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 3;
                            else driver.baseFuel -= 5;
                            break;

                        case 2: // Overheat
                            System.out.println("🔥 Engine Overheat! -10 Fuel or Pay ₱5");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 5;
                            else driver.baseFuel -= 10;
                            break;

                        case 3: // LTO
                            System.out.println("🚨 LTO Stop! Pay ₱8 or lose 3 fuel + 1 passenger.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 8;
                            else {
                                driver.baseFuel -= 3;
                                //NEW LINE OF CODES==========
                                // Use PassengerList instead of simple counter
                                if (passengerList.getPassengerCount() > 0) {
                                    passengerList.removeRandomPassenger();
                                } else {
                                    System.out.println("No passengers to remove!");
                                }
                            }
                            break;

                        case 4: // Fuel leak
                            System.out.println("⛽ Fuel Leak! -4 Fuel or Pay ₱7");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 7;
                            else driver.baseFuel -= 4;
                            break;

                        case 5: // Thief
                            System.out.println("🚐 Thief! 1 passenger stolen. Pay ₱10 to stop him.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 10;
                            else {
                                //NEW LINE OF CODES==========
                                // Use PassengerList instead of simple counter
                                if (passengerList.getPassengerCount() > 0) {
                                    passengerList.removeRandomPassenger();
                                } else {
                                    System.out.println("No passengers to remove!");
                                }
                                driver.baseFuel -= rand.nextInt(2) + 1;
                            }
                            break;
                    }

                    System.out.println("\n📊 Status Update: Passengers: " + passengers +
                            " | Fuel: " + driver.baseFuel +
                            " | Money: ₱" + money);
                }

                if (driver.baseFuel <= 0) {
                    System.out.println("❌ You ran out of fuel!");
                    failedRun = true;
                    break;
                }
            }

            // ======================================================================
            // END OF MAP — CHECK FAIL / SUCCESS
            // ======================================================================

            if (failedRun) {
                if (retryPrompt(driver, boss)) continue;
                break;
            }

            if (money < 70) {
                System.out.println("💰 Not enough money to complete mission.");

                for (int play = 0; play != 1; play = InputHandler.getInt("Press 1 to Continue: ")) {
                }
                retryPrompt(driver, boss);
                return play(driver);
            }

            // ====================== SHOP ======================
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("\n🎁 SHOP TIME!");
            boolean buying = true;
            while (buying) {
                System.out.println("\nYour money: ₱" + money);
                System.out.println("Your Fuel: " + driver.baseFuel);
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
                        System.out.println("1. RePhil (+40 Fuel) - ₱10");
                        System.out.println("2. Burning Tire (+30 dmg) - ₱10");
                        System.out.println("3. Bumper Shield (Block 30 dmg) - ₱10");
                        System.out.println("4. Back");
                        int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);

                        if (itemChoice == 4) continue;

                        if (money < 10) {
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
                        money -= 10;
                        driver.buyItem(selectedItem);
                        System.out.println("\n✅ You bought 1x " + selectedItem + "! (" + driver.inventory.get(selectedItem) + "x total)");
                    }

                    case 2 -> { // ===== RESELL SECTION =====
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

                        int resellPrice = 5;

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
                System.out.println("\n✅ Boss defeated!");
                System.out.println("🎉 Mission Complete!");
                System.out.println("Passengers: " + passengers + " | Total ₱" + money);
                System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                System.out.println("🎉 You unlocked 2nd skill");
                missionComplete = true;
                return true;
            }
        }
        return false;
    }

    public boolean retryPrompt(Driver driver, Bossing boss) {
        int retryChoice = InputHandler.getChoice("\n🔁 Try again Map 1? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = retryChoice == 1;
        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map 1 from Stop 1...");
        } else {

            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }

    // Make this method public so BossFight can call it if needed
    public void resetMap(Driver driver, Bossing boss) {

        driver.baseFuel = 150;
        driver.inventory.clear();
        boss.fuel = 200;
        passengers = 0;
        money = 0;
        passengerList = new PassengerList(driver, this);
    }





}