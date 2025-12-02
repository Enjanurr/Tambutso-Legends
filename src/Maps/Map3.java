package Maps;

import AsciiArts.*;
import Boss.*;
import Characters.Driver;
import Utils.*;
import java.util.*;
import main.Main;

public class Map3 extends World {
    Bossing boss = new SirKhai();
    public int money;
    public int passengers;
    int stop = 0;
    private Random rand = new Random();
    private boolean bossPassive = false;
    private PassengerList passengerList;
    private Driver driver;

    public Map3() {
        super(70, 20); // gaba = 70%, stops = 20
        passengerList = new PassengerList(driver,this);
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;

        String[] destination = {
                "CIT-University",
                "E-mall (Elizabeth Mall)",
                "Colon Street",
                "Cebu Metropolitan Cathedral",
                "Basilica Minore del Santo Niño",
                "Magellan’s Cross",
                "Cebu City Hall",
                "Pier 1",
                "Pier 3",
                "SM City Cebu",
                "Mabolo Church",
                "F. Cabahug Street",
                "Panagdait",
                "Archbishop Reyes Avenue",
                "Ayala Center Cebu",
                "Cebu Business Park",
                "Escario Street",
                "Gorordo Avenue",
                "JY Square Mall",
                "IT Park"
        };

        while (!missionComplete) {
            driver.baseFuel = 300;
            passengers = 0;
            money = 0;
            boolean failedRun = false;

            AsciiArt.printSunrise();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\n🚏 Starting Map 3: CIT-U → IT Park (" + stops + " stops)");
            System.out.println("Mission: Earn ₱150+ from 20 stops and Defeat Sir Khai.\n");



            // === STOPS PHASE ===
            for (stop = 1; stop <= stops; stop++) {

                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("=== Map 3: CIT-U → IT Park  ===");
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

                    try {
                        AsciiArt.printMap3Jeepney();
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

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
                        passengers++; // Increment your passenger count
                    }

                    System.out.println("🚘 Passengers Onboard: " + passengerList.getPassengerCount());

                }

                // ================ ACTION: SKIP STOP ================
                else if (action == 2) {
                    int fuelLoss = rand.nextInt(2) + 5;
                    driver.baseFuel -= fuelLoss;
                    System.out.println("You skipped this stop (Fuel -" + fuelLoss + ")");

                    try {
                        AsciiArt.printMap2Jeepney();
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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

                    System.out.println("\n===============================");
                    System.out.println(" ⚠ RANDOM EVENT OCCURRED! ");
                    System.out.println("===============================");
                    System.out.println("Event: " + randomGaba);

                    switch (randomGaba) {
                        case 1: // Flat Tire
                            System.out.println("🚗 Flat Tire! -5 Fuel or Pay ₱10");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 10;
                            else driver.baseFuel -= 5;
                            break;

                        case 2: // Overheat
                            System.out.println("🔥 Engine Overheat! -12 Fuel or Pay ₱10");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 10;
                            else driver.baseFuel -= 10;
                            break;

                        case 3: // LTO
                            System.out.println("🚨 LTO Stop! Pay ₱15 or lose 3 fuel + 1 passenger.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 15;
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
                            System.out.println("⛽ Fuel Leak! -4 Fuel or Pay ₱8");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 8;
                            else driver.baseFuel -= 4;
                            break;

                        case 5: // Thief
                            System.out.println("🚐 Thief! 1 passenger stolen. Pay ₱13 to stop him.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 13;
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

            if (failedRun) {
                if (retryPrompt(driver, boss)) continue;
                else break;
            }
            //checks if money is over the mission, if not it will require to restant the map
            if (money < 150) {
                System.out.println("💰 Not enough money to complete mission.");

                for (int play = 0; play != 1; play = InputHandler.getInt("Press 1 to Continue: ")) {}
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
                        System.out.println("1. RePhil (+50 Fuel) - ₱20");
                        System.out.println("2. Burning Tire (+40 dmg) - ₱20");
                        System.out.println("3. Bumper Shield (Block 40 dmg) - 20");
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
            try {
                Thread.sleep(1000);
                AsciiArt.printEncounter();
                Thread.sleep(1000);
                AsciiArt.printSirKhaiTitleArt();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            BossFight bossFight = new BossFight(boss, driver, this);
            int result = bossFight.start();

            if (result == -1) {
                // Restart the entire map
                resetMap(driver, boss);
                return play(driver); // Recursively call play to restart the map
            }

            if (result == 1) {
                System.out.println("✅ You defeated " + boss.name + "!");
                money += 300;
                driver.setMoney(money);
                System.out.println("💎 Reward: ₱300 | Total Money: ₱" + money);

                System.out.println("🏁 Final form achieved!");
                System.out.println("SirKhai has evolved into JolliKhai");
                System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                missionComplete = true;
                return true;
            }

        }
        return false;
    }

    public boolean retryPrompt(Driver driver, Bossing boss) {
        int retryChoice = InputHandler.getChoice("\n🔁 Try again Map 3? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = retryChoice == 1;
        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map 3 from Stop 1...");
        } else {

            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }


    public void resetMap(Driver driver, Bossing boss) {
        bossPassive = false;
        driver.baseFuel = 300;
        driver.inventory.clear();
        boss.fuel = 350;
        passengers = 0;
        money = 0;
        passengerList = new PassengerList(driver, this);
    }
}