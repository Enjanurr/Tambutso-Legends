
package Maps;

import Boss.BossVaughn;
import Boss.Bossing;
import Characters.Driver;
import Utils.InputHandler;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import main.Main;

public class Map1 extends World {
    List<Passenger> passengerList = new ArrayList();
    int passengerIdCounter = 1;
    int stop = 0;
    private Random rand = new Random();
    private boolean bossPassive = false;
    Bossing boss = new BossVaughn();

    public void showPassengerList(int currentStop) {

        while(!this.passengerList.isEmpty()) {
            this.passengerList.sort(Comparator.comparingInt((px) -> px.dropStop));

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("     PASSENGER LIST (STOP " + currentStop + ")   ");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("ID | Type        | Stop | Fare");
            System.out.println("--------------------------------");

            for(Passenger p : this.passengerList) {
                System.out.printf("%-3d %-12s %-6d ₱%d\n", p.id, p.type, p.dropStop, p.fare);
            }

            System.out.println("--------------------------------");
            System.out.println("Passengers onboard: " + this.passengerList.size());
            System.out.println("--------------------------------");
            System.out.println("Press 1 to DROP passenger(s)");
            System.out.println("Press 0 to GO BACK");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();


            if (!input.matches("[01]")) {
                System.out.println("❌ Invalid input! Please enter only 0 or 1.");
            } else {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    --this.stop;
                    return;
                }

                System.out.print("Enter Passenger IDs to drop (separated by space): ");
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                List<Integer> dropIDs = new ArrayList();

                for(String part : parts) {
                    try {
                        dropIDs.add(Integer.parseInt(part));
                    } catch (NumberFormatException var15) {
                        System.out.println("❌ Invalid input ignored: " + part);
                    }
                }

                if (dropIDs.isEmpty()) {
                    System.out.println("❌ No valid Passenger IDs entered!");
                } else {
                    for(int pid : dropIDs) {
                        Passenger target = null;

                        for(Passenger p : this.passengerList) {
                            if (p.id == pid) {
                                target = p;
                                break;
                            }
                        }

                        if (target == null) {
                            System.out.println("❌ Passenger ID " + pid + " not found! Skipping.");
                        } else if (target.dropStop > currentStop) {
                            System.out.println("\n❌ Cannot drop Passenger " + pid + ". Their stop (" + target.dropStop + ") is ahead of this stop (" + currentStop + ").");
                        } else {
                            int confirm = InputHandler.getChoice("Drop Passenger " + pid + "? (1 Yes | 2 No): ", 1, 2);
                            if (confirm == 1) {
                                if (target.dropStop == currentStop) {
                                    System.out.println("✔ Correct stop! Added full fare: ₱" + target.fare);
                                    this.money += target.fare;
                                } else if (currentStop > target.dropStop) {
                                    int missedStops = currentStop - target.dropStop;
                                    int totalReduction = missedStops * 15;
                                    int reducedFare = Math.max(0, target.fare - totalReduction);
                                    System.out.println("❌ Missed stop(s)! each missed stop reduces fare by ₱15");
                                    System.out.println("➡ Passenger missed " + missedStops + " stop(s). Fare reduced by ₱" + totalReduction + ".");
                                    System.out.println("\ud83d\udcb0 Added to money: ₱" + reducedFare);
                                    this.money += reducedFare;
                                }

                                this.passengerList.remove(target);
                                --this.passengers;
                                System.out.println("Passenger " + pid + " dropped. \ud83d\udcb0 Money now: ₱" + this.money);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nNo passengers onboard.");
    }

    public Map1() {
        super(30, 10);
    }

    public boolean play(Driver driver) {
        boolean missionComplete = false;
        String[] destination = new String[]{"Naga City Jeepney Terminal", "KEPCO", "Inoburan Stop ", "Tinaan Crossing", "Langtad Stop", "Cantao-an Junction", "Tunghaan Stop (Boundary Area)", "Calajo-an Stop", "Tulic Stop", "Minglanilla Public Market / Town Proper"};
        int actionUsed = 0;

        while(!missionComplete) {

            driver.baseFuel = 150;
            this.passengers = 0;
            this.money = 0;
            boolean failedRun = false;
            System.out.println("\n\ud83d\ude8f Starting Map 1: Naga to Minglanilla (" + this.stops + " stops)");
            System.out.println("Mission: Earn ₱300 from 10 stops and Defeat Boss Vaughn.\n");

            for(this.stop = 1; this.stop <= this.stops; ++this.stop) {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("\n=== Map 1: Naga to Minglanilla ===");
                System.out.println("\nStop " + this.stop + " | Destination: " + destination[this.stop - 1]);
                System.out.println("------------------------");
                System.out.println("Fuel: " + driver.baseFuel + " | Passengers: " + this.passengers + " | Money: ₱" + this.money);
                System.out.println("1. Pick up passengers");
                System.out.println("2. Skip stop (save fuel)");
                System.out.println("3. View passenger list");
                System.out.println("------------------------");
                System.out.println("0. Back to Main Menu");

                int action = InputHandler.getChoice("Your choice: ", 1, 3);

                if (action == 0) {
                    int choice = InputHandler.getChoice("Do you want to go back to Main Menu? (1 - Yes, 2 - No): ", 1, 2);
                    if (choice == 1) {
                        System.out.println("\n\ud83d\udd19 Returning to Main Menu...");
                        Main.main((String[])null);
                        return false;
                    }

                    --this.stop;
                } else {
                    if (action == 1) {
                        if (this.passengerList.size() >= 13) {
                            System.out.println("\n⚠ Cannot pick up passengers.");
                            System.out.println("\ud83d\ude8c Your jeepney is FULL (13 / 13).");
                            System.out.println("➡ Drop some passengers first!");
                            --this.stop;
                            continue;
                        }

                        Random rand = new Random();
                        int fuelLoss = rand.nextInt(3) + 8;
                        driver.baseFuel -= fuelLoss;
                        if (driver.baseFuel <= 0) {
                            driver.baseFuel = 0;
                            System.out.println("❌ You ran out of fuel! Game Over.");
                            failedRun = true;
                            break;
                        }

                        int countChance = rand.nextInt(4);
                        if (countChance == 0) {
                            System.out.println("\nNo passengers at this stop.");
                            System.out.println("\ud83d\udd0b Fuel Used: " + fuelLoss + " | Remaining Fuel: " + driver.baseFuel);
                            actionUsed = 1;
                            continue;
                        }

                        System.out.println("\n\ud83d\ude96 Passenger Pickup");
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                        System.out.println("\ud83e\uddcd Picked Up: " + countChance + " passenger(s)");
                        System.out.println("\ud83d\udd0b Fuel Used: " + fuelLoss + " | Remaining Fuel: " + driver.baseFuel);
                        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━");

                        for(int i = 0; i < countChance; ++i) {
                            if (this.passengerList.size() >= 13) {
                                System.out.println("⚠ Passenger limit reached (13). Cannot add more.");
                                break;
                            }

                            int t = rand.nextInt(3);
                            String type;
                            int fare;
                            if (t == 0) {
                                type = "Student";
                                fare = 40 + rand.nextInt(31);
                            } else if (t == 1) {
                                type = "Senior";
                                fare = 60 + rand.nextInt(31);
                            } else {
                                type = "Worker";
                                fare = 100 + rand.nextInt(41);
                            }

                            int dropStop = this.stop + 1 + rand.nextInt(Math.max(1, this.stops - this.stop));
                            if (dropStop == this.stop) {
                                System.out.println("❌ Cannot add passenger " + this.passengerIdCounter + ". Their drop stop (" + dropStop + ") is the current stop.");
                                System.out.println("➡ Skipping this passenger.\n");
                            } else {
                                this.passengerList.add(new Passenger(this.passengerIdCounter, type, dropStop, fare));
                                System.out.println("Passenger " + this.passengerIdCounter + " (" + type + ") | Drop at Stop: " + dropStop + " | Fare: ₱" + fare);
                                ++this.passengerIdCounter;
                                ++this.passengers;
                            }
                        }

                        System.out.println("\ud83d\ude98 Passengers Onboard: " + this.passengerList.size());
                        actionUsed = 1;
                    } else if (action == 2) {
                        int fuelLoss = this.rand.nextInt(2) + 5;
                        driver.baseFuel -= fuelLoss;
                        System.out.println("You skipped this stop (Fuel -" + fuelLoss + ").");
                        actionUsed = 2;
                    } else if (action == 3) {
                        this.showPassengerList(this.stop);
                        actionUsed = 3;
                        continue;
                    }

                    if (this.rand.nextInt(100) < this.gaba) {
                        if (this.money <= 0) {
                            System.out.println("\n\ud83d\udcb8 You have no money. The gaba event was skipped!");
                            continue;
                        }

                        int randomGaba = this.rand.nextInt(5) + 1;
                        System.out.println("\n===============================");
                        System.out.println(" ⚠️  A RANDOM EVENT OCCURRED! ");
                        System.out.println("===============================");
                        System.out.println(" → Event: " + randomGaba + "\n");
                        switch (randomGaba) {
                            case 1:
                                System.out.println("\ud83d\ude97 Flat Tire! -5 Fuel, pay ₱15 to fix.");
                                int choice = InputHandler.getChoice("1 - pay, 2 - ignore: ", 1, 2);
                                if (choice == 1) {
                                    this.money -= 15;
                                } else {
                                    driver.baseFuel -= 5;
                                }
                                break;
                            case 2:
                                System.out.println("\ud83d\udd25 Engine Overheated! -10 Fuel, pay ₱10 to cool.");
                                int choice = InputHandler.getChoice("1 - pay, 2 - ignore: ", 1, 2);
                                if (choice == 1) {
                                    this.money -= 10;
                                } else {
                                    driver.baseFuel -= 10;
                                }
                                break;
                            case 3:
                                System.out.println("\ud83d\udea8 LTO Stop! Pay ₱20 fine or lose 3 fuel and 1 passenger.");
                                int choice = InputHandler.getChoice("1 - pay, 2 - ignore: ", 1, 2);
                                if (choice == 1) {
                                    this.money -= 20;
                                } else {
                                    driver.baseFuel -= 3;
                                    this.passengers = Math.max(0, this.passengers - 1);
                                }
                                break;
                            case 4:
                                System.out.println("⛽ Fuel Leak! -4 Fuel, pay ₱12 to repair.");
                                int choice = InputHandler.getChoice("1 - pay, 2 - ignore: ", 1, 2);
                                if (choice == 1) {
                                    this.money -= 12;
                                } else {
                                    driver.baseFuel -= 4;
                                }
                                break;
                            case 5:
                                int stolen = 1;
                                System.out.println("\ud83d\ude90 Jeepney Thief! Lost " + stolen + " passenger. Pay ₱18 bribe to recover.");
                                int choice = InputHandler.getChoice("1 - pay, 2 - ignore: ", 1, 2);
                                if (choice == 1) {
                                    this.money -= 18;
                                } else {
                                    this.passengers = Math.max(0, this.passengers - stolen);
                                    driver.baseFuel -= this.rand.nextInt(2) + 1;
                                }
                        }

                        System.out.println("\n\ud83d\udcca Status Update: Passengers: " + this.passengers + ", Fuel: " + driver.baseFuel + ", Money: ₱" + this.money);
                    }

                    if (driver.baseFuel <= 0) {
                        System.out.println("❌ You ran out of fuel!");
                        failedRun = true;
                        break;
                    }
                }
            }

            if (failedRun) {
                if (this.retryPrompt(driver, this.boss)) {
                    continue;
                }
                break;
            } else {
                if (this.money < 300) {
                    System.out.println("\ud83d\udcb8 You don’t have enough money to complete this mission.");
                    System.out.println("\ud83d\udd01 We recommend restarting the map to try again.");

                    for(int play = 0; play != 1; play = InputHandler.getInt("Press 1 to Continue: ")) {
                    }

                    return this.play(driver);
                }

                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("\n\ud83c\udf81 SHOP TIME!");
                boolean buying = true;

                while(buying) {
                    System.out.println("\nYour money: ₱" + this.money);
                    System.out.println("Your Fuel: " + driver.baseFuel);
                    System.out.println("1. Buy Item");
                    System.out.println("2. Resell Item");
                    System.out.println("3. Exit Shop (Continue to Boss)");
                    System.out.println("------------------------");
                    System.out.println("0. Back to Main Menu");
                    int mainChoice = InputHandler.getChoice("Choose: ", 0, 3);
                    switch (mainChoice) {
                        case 0:
                            int choice = InputHandler.getChoice("Do you want to go back to Main Menu? (1 - Yes, 2 - No): ", 1, 2);
                            if (choice == 1) {
                                System.out.println("\n\ud83d\udd19 Returning to Main Menu...");
                                Main.main((String[])null);
                                return false;
                            }
                            continue;
                        case 1:
                            System.out.println("\n\ud83d\uded2 WHAT DO YOU WANT TO BUY?");
                            System.out.println("1. RePhil (+30 Fuel) - ₱30");
                            System.out.println("2. Burning Tire (+20 dmg) - ₱30");
                            System.out.println("3. Bumper Shield (Block 20 dmg) - ₱30");
                            System.out.println("4. Back");
                            int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);
                            if (itemChoice == 4) {
                                continue;
                            }

                            if (this.money < 30) {
                                System.out.println("\n\ud83d\udcb8 You don't have enough money to buy another item.");
                                continue;
                            }

                            String var10000;
                            switch (itemChoice) {
                                case 1 -> var10000 = "RePhil";
                                case 2 -> var10000 = "Burning Tire";
                                case 3 -> var10000 = "Bumper Shield";
                                default -> var10000 = "";
                            }

                            byte var72;
                            switch (var10000) {
                                case "RePhil" -> var72 = 3;
                                case "Burning Tire" -> var72 = 5;
                                case "Bumper Shield" -> var72 = 3;
                                default -> var72 = 5;
                            }

                            int itemLimit = var72;
                            int currentQty = (Integer)driver.inventory.getOrDefault(selectedItem, 0);
                            if (currentQty >= itemLimit) {
                                System.out.println("\n⚠️ You already have the maximum amount of " + selectedItem + " (" + itemLimit + "x)!");
                                continue;
                            }

                            this.money -= 30;
                            driver.buyItem(selectedItem);
                            System.out.println("\n✅ You bought 1x " + selectedItem + "! (" + String.valueOf(driver.inventory.get(selectedItem)) + "x total)");
                            break;
                        case 2:
                            if (driver.inventory.isEmpty()) {
                                System.out.println("\n❌ You have nothing to sell!");
                                continue;
                            }

                            System.out.println("\n\ud83d\udcb0 WHAT DO YOU WANT TO SELL?");
                            int i = 1;
                            List<String> items = new ArrayList(driver.inventory.keySet());

                            for(String item : items) {
                                System.out.println(i + ". " + item + " (x" + String.valueOf(driver.inventory.get(item)) + ")");
                                ++i;
                            }

                            System.out.println(i + ". Back");
                            int sellChoice = InputHandler.getChoice("Choose: ", 1, i);
                            if (sellChoice == i) {
                                continue;
                            }

                            String itemToSell = (String)items.get(sellChoice - 1);
                            int quantity = (Integer)driver.inventory.get(itemToSell);
                            int resellPrice = 30;
                            driver.inventory.put(itemToSell, quantity - 1);
                            if ((Integer)driver.inventory.get(itemToSell) <= 0) {
                                driver.inventory.remove(itemToSell);
                            }

                            this.money += resellPrice;
                            System.out.println("\n\ud83d\udcb5 You sold 1x " + itemToSell + " for ₱" + resellPrice + "!");
                            break;
                        case 3:
                            System.out.println("\ud83d\udc4b Leaving shop...");
                            buying = false;
                    }

                    System.out.println("\ud83d\udcb0 Remaining Money: ₱" + this.money);
                    System.out.println("\n\ud83c\udf92 Current Inventory:");
                    if (driver.inventory.isEmpty()) {
                        System.out.println("❌ Your inventory is empty!");
                    } else {
                        for(Map.Entry<String, Integer> entry : driver.inventory.entrySet()) {
                            PrintStream var73 = System.out;
                            String var10001 = (String)entry.getKey();
                            var73.println("• " + var10001 + " (x" + String.valueOf(entry.getValue()) + ")");
                        }
                    }
                }

                Bossing boss = new BossVaughn();
                System.out.println("\n========== ⚔️ BOSS FIGHT START ==========");
                System.out.println("\ud83d\ude8d " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
                System.out.println("\ud83e\uddd1\u200d✈️ Driver: " + driver.name + " (Fuel: " + driver.baseFuel + ")");
                System.out.println("------------------------------------------");
                System.out.println("\ud83d\udca1 1 Skill1 unlocked for this map!");
                boolean defeatBoss = false;
                int shieldActive = 0;
                int cooldownSkill1 = 0;
                int bossUltimateCD = 0;
                boolean rePhilUsed = false;
                boolean burningTireUsed = false;
                boolean bumperShieldUsed = false;
                int rounds = 1;

                while(!defeatBoss) {
                    System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    System.out.println("\n     ⚔️  ROUND " + rounds + "  ⚔️");
                    System.out.println("\n--- Player Turn ---");
                    System.out.println("Fuel: " + driver.baseFuel + " | Boss Fuel: " + boss.fuel);
                    String var74 = cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : "";
                    System.out.println("1. Use Skill 1" + var74);
                    System.out.println("2. Use Item");
                    System.out.println("3. Skip Turn ( +(5-10) Fuel)");
                    System.out.println("----------------------");
                    System.out.println("0. Exit Fight(Restart Current Map)");
                    int choice = InputHandler.getChoice("Your choice: ", 0, 4);
                    int damage = 0;
                    boolean validTurn = true;
                    switch (choice) {
                        case 0:
                            if (this.retryPrompt(driver, boss)) {
                                return this.play(driver);
                            }
                            break;
                        case 1:
                            if (cooldownSkill1 > 0) {
                                System.out.println("⚠️ Skill 1 is cooling down! Wait " + cooldownSkill1 + " more turn(s).");
                                validTurn = false;
                            } else {
                                damage = driver.skill1();
                                cooldownSkill1 = 1;
                            }
                            break;
                        case 2:
                            if (driver.inventory.isEmpty()) {
                                System.out.println("\n❌ You have no items to use!");
                                validTurn = false;
                            } else {
                                System.out.println("\n\ud83c\udf92 Available Items:");
                                int optionNum = 1;
                                HashMap<Integer, String> menuMap = new HashMap();

                                for(Map.Entry<String, Integer> e : driver.inventory.entrySet()) {
                                    System.out.println(optionNum + ". " + (String)e.getKey() + " (x" + String.valueOf(e.getValue()) + ")");
                                    menuMap.put(optionNum, (String)e.getKey());
                                    ++optionNum;
                                }

                                System.out.println(optionNum + ". Exit");
                                menuMap.put(optionNum, "Exit");
                                int chooseItem = InputHandler.getChoice("Your choice: ", 1, optionNum);
                                String chosenItem = (String)menuMap.get(chooseItem);
                                if (chosenItem.equals("Exit")) {
                                    System.out.println("❌ You cancelled using an item.");
                                    validTurn = false;
                                } else {
                                    switch (chosenItem) {
                                        case "RePhil":
                                            if (rePhilUsed) {
                                                System.out.println("❌ You already used RePhil once! You can’t use it again.");
                                            } else {
                                                driver.baseFuel += 30;
                                                driver.decreaseItem("RePhil");
                                                rePhilUsed = true;
                                                System.out.println("⛽ RePhil used! +30 Fuel (" + driver.baseFuel + ")");
                                            }
                                            break;
                                        case "Burning Tire":
                                            if (burningTireUsed) {
                                                System.out.println("❌ You already used Burning Tire once! You can’t use it again.");
                                            } else {
                                                boss.fuel -= 20;
                                                driver.decreaseItem("Burning Tire");
                                                burningTireUsed = true;
                                                System.out.printf("\ud83d\udd25 Burning Tire used! -20 Boss fuel (Remaining: %d)%n", boss.fuel);
                                            }
                                            break;
                                        case "Bumper Shield":
                                            if (bumperShieldUsed) {
                                                System.out.println("❌ You already used Bumper Shield once! You can’t use it again.");
                                            } else {
                                                shieldActive = 20;
                                                driver.decreaseItem("Bumper Shield");
                                                bumperShieldUsed = true;
                                                System.out.println("\ud83d\udee1️ Shield activated! Blocks next 20 damage");
                                            }
                                            break;
                                        default:
                                            System.out.println("❌ Invalid item choice.");
                                    }

                                    validTurn = false;
                                }
                            }
                            break;
                        case 3:
                            int fuelGain = this.rand.nextInt(6) + 5;
                            driver.baseFuel += fuelGain;
                            System.out.println(driver.name + " takes a breather and recovers +" + fuelGain + " fuel (" + driver.baseFuel + ")");
                            validTurn = true;
                    }

                    if (validTurn && damage > 0) {
                        rePhilUsed = false;
                        burningTireUsed = false;
                        bumperShieldUsed = false;
                        boss.fuel -= damage;
                        if (boss.fuel < 0) {
                            boss.fuel = 0;
                        }

                        System.out.println("\ud83d\udca5 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
                    }

                    if (!this.bossPassive) {
                        if (validTurn && boss.fuel > 0) {
                            System.out.println("\n--- Boss Turn ---");
                            int bossDamage = 0;
                            ++rounds;
                            if (bossUltimateCD == 0 && this.rand.nextInt(2) == 0) {
                                bossDamage = boss.ultimate();
                                bossUltimateCD = 5;
                            } else {
                                bossDamage = boss.attackSkill();
                            }

                            if (shieldActive > 0) {
                                int blocked = Math.min(shieldActive, bossDamage);
                                bossDamage -= blocked;
                                shieldActive -= blocked;
                                System.out.println("\ud83d\udee1️ Shield blocked " + blocked + " damage! Remaining shield: " + shieldActive);
                            }

                            driver.baseFuel -= bossDamage;
                            if (driver.baseFuel < 0) {
                                driver.baseFuel = 0;
                            }

                            System.out.println("\ud83d\udd25 Boss dealt " + bossDamage + "! Your fuel left: " + driver.baseFuel);
                        }

                        if (bossUltimateCD > 0) {
                            --bossUltimateCD;
                        }

                        if (validTurn && cooldownSkill1 > 0) {
                            --cooldownSkill1;
                        }
                    } else {
                        System.out.println("\ud83d\ude10 The boss stands still and doesn’t attack...");
                    }

                    if (driver.baseFuel <= 0) {
                        System.out.println("\n\ud83d\udc80 Defeated by " + boss.name + "! You failed to protect the passengers...");
                        if (this.retryPrompt(driver, boss)) {
                            return this.play(driver);
                        }

                        System.out.println("\ud83d\udc4b You chose not to retry. Game Over.");
                        return false;
                    }

                    if (boss.fuel <= 0) {
                        System.out.println("\n✅ Boss defeated!");
                        driver.levelUp(2);
                        defeatBoss = true;
                        if (this.money >= 300) {
                            System.out.println("\ud83c\udf89 Mission Complete!");
                            System.out.println("Passengers: " + this.passengers + " | Total ₱" + this.money);
                            System.out.println("\ud83c\udf89 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                            System.out.println("\ud83c\udf89 You unlocked 2nd skill");
                            missionComplete = true;
                            return true;
                        }

                        System.out.println("⚠️ Mission Incomplete!");
                    }
                }
            }
        }

        return false;
    }

    private boolean useItem(Driver driver, Bossing boss, int fuel, int shieldActive) {
        HashMap<String, Integer> inv = driver.getInventory();
        if (inv.isEmpty()) {
            System.out.println("\n❌ You have no items to use!");
            return false;
        } else {
            System.out.println("\n\ud83c\udf92 Available Items:");
            int optionNum = 1;
            HashMap<Integer, String> menuMap = new HashMap();

            for(Map.Entry<String, Integer> e : inv.entrySet()) {
                System.out.println(optionNum + ". " + (String)e.getKey() + " (x" + String.valueOf(e.getValue()) + ")");
                menuMap.put(optionNum, (String)e.getKey());
                ++optionNum;
            }

            System.out.println(optionNum + ". Cancel");
            menuMap.put(optionNum, "Cancel");
            int itemChoice = InputHandler.getChoice("Use which item? ", 1, optionNum);
            String chosenItem = (String)menuMap.get(itemChoice);
            if (chosenItem.equals("Cancel")) {
                System.out.println("❌ Cancelled item use.");
                return false;
            } else {
                switch (chosenItem) {
                    case "RePhil":
                        fuel += 30;
                        driver.decreaseItem("RePhil");
                        System.out.println("⛽ RePhil used! +30 fuel (" + fuel + ")");
                        break;
                    case "Burning Tire":
                        boss.fuel -= 20;
                        driver.decreaseItem("Burning Tire");
                        System.out.println("\ud83d\udd25 Burning Tire used! -20 Boss fuel");
                        break;
                    case "Bumper Shield":
                        shieldActive = 20;
                        driver.decreaseItem("Bumper Shield");
                        System.out.println("\ud83d\udee1️ Shield activated! Blocks 20 next damage");
                }

                return true;
            }
        }
    }

    private boolean retryPrompt(Driver driver, Bossing boss) {
        int choice = InputHandler.getChoice("\n\ud83d\udd01 Try again Map 1? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = choice == 1;
        if (retry) {
            this.resetMap(driver, boss);
            System.out.println("\n\ud83d\udd01 Restarting Map 1 from Stop 1...");
        } else {
            this.bossPassive = true;
            System.out.println("\n\ud83d\udd4a️ You chose not to retry — the fight continues");
        }

        return retry;
    }

    private void resetMap(Driver driver, Bossing boss) {
        this.bossPassive = false;
        driver.baseFuel = 150;
        driver.inventory.clear();
        boss.fuel = 250;
        this.passengers = 0;
        this.money = 0;
        this.passengerList = new ArrayList();
        this.passengerIdCounter = 0;
    }

    static class Passenger {
        int id;
        String type;
        int dropStop;
        int fare;

        Passenger(int id, String type, int dropStop, int fare) {
            this.id = id;
            this.type = type;
            this.dropStop = dropStop;
            this.fare = fare;
        }
    }
}
