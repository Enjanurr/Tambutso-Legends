package Maps;


import Boss.*;
import Characters.Driver;
import Utils.InputHandler;
import java.util.*;
import main.Main;


public class Map1 extends World {

    int stop = 0;
    private Random rand = new Random();
    private boolean bossPassive = false;
    Bossing boss = new BossVaughn();


    private  PassengerList passengerList;
    //private PassengerList passengerList = new PassengerList();
    public int money;
    public int passengers;
    private Driver driver;

// In your initialization code:


    public Map1() {
        super(30, 10);

        passengerList = new PassengerList(driver,this); // <--- THIS MAP INSTANCE
    }

    public boolean play(Driver driver) {
        passengerList = new PassengerList(driver, this);
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
            System.out.println("Mission: Earn ₱100 from 10 stops and defeat Boss Vaughn.\n");

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
                        passengers++; // Increment your passenger count
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

                    System.out.println("\n===============================");
                    System.out.println(" ⚠ RANDOM EVENT OCCURRED! ");
                    System.out.println("===============================");
                    System.out.println("Event: " + randomGaba);

                    switch (randomGaba) {
                        case 1: // Flat Tire
                            System.out.println("🚗 Flat Tire! -5 Fuel or Pay ₱15");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 15;
                            else driver.baseFuel -= 5;
                            break;

                        case 2: // Overheat
                            System.out.println("🔥 Engine Overheat! -10 Fuel or Pay ₱10");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 10;
                            else driver.baseFuel -= 10;
                            break;

                        case 3: // LTO
                            System.out.println("🚨 LTO Stop! Pay ₱20 or lose 3 fuel + 1 passenger.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 20;
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
                            System.out.println("⛽ Fuel Leak! -4 Fuel or Pay ₱12");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 12;
                            else driver.baseFuel -= 4;
                            break;

                        case 5: // Thief
                            System.out.println("🚐 Thief! 1 passenger stolen. Pay ₱18 to stop him.");
                            if (InputHandler.getChoice("1-pay, 2-ignore: ", 1, 2) == 1)
                                money -= 18;
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

            if (money < 100) {
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
                // ADDED BACK TO MAIN MENU
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
                    case 1 -> { // ===== BUY SECTION =====
                        System.out.println("\n🛒 WHAT DO YOU WANT TO BUY?");
                        System.out.println("1. RePhil (+30 Fuel) - ₱10");
                        System.out.println("2. Burning Tire (+20 dmg) - ₱10");
                        System.out.println("3. Bumper Shield (Block 20 dmg) - ₱10");
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
                        money -= 10; // for the price tweak it here
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

                        // Set resell price (e.g., half of original price)
                        int resellPrice = 5;

                        // Update inventory and money
                        driver.inventory.put(itemToSell, quantity - 1);
                        if (driver.inventory.get(itemToSell) <= 0) {
                            driver.inventory.remove(itemToSell);
                        }

                        money += resellPrice;
                        System.out.println("\n💵 You sold 1x " + itemToSell + " for ₱" + resellPrice + "!");
                    }

                    case 3 -> { // ===== EXIT =====
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



            // Task to do , don't make the boss attack after canceling the item


            // ====================== BOSS FIGHT ======================
            Bossing boss = new BossVaughn();
            System.out.println("\n========== ⚔️ BOSS FIGHT START ==========");
            System.out.println("🚍 " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
            System.out.println("🧑‍✈️ Driver: " + driver.name + " (Fuel: " + driver.baseFuel + ")");
            System.out.println("------------------------------------------");
            System.out.println("💡 1 Skill1 unlocked for this map!");

            boolean defeatBoss = false;
            int shieldActive = 0;
            int cooldownSkill1 = 0;
            int bossUltimateCD = 0; // ✅ move this OUTSIDE the while loop

            // ===========Limits to item use to 1x only===========
            boolean rePhilUsed = false;
            boolean burningTireUsed = false;
            boolean bumperShieldUsed = false;
            int rounds = 1;

            while (!defeatBoss) {
                System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("\n     ⚔️  ROUND " + rounds + "  ⚔️");
                System.out.println("\n--- Player Turn ---");
                System.out.println("Fuel: " + driver.baseFuel + " | Boss Fuel: " + boss.fuel);
                System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
                System.out.println("2. Use Item");
                System.out.println("3. Skip Turn ( +(5-10) Fuel)");
                System.out.println("----------------------");
                System.out.println("0. Exit Fight(Restart Current Map)");
                int choice = InputHandler.getChoice("Your choice: ", 0, 4);

                int damage = 0;
                boolean validTurn = true;
                switch (choice) {
                    case 0 -> {
                        if (retryPrompt(driver, boss)) {
                            // Reset all player and mission stats
                            // 👇 Restart the entire map loop instead of continuing boss fight
                            return play(driver);
                        }

                    }
                    case 1 -> {
                        if (cooldownSkill1 > 0) {
                            System.out.println("⚠️ Skill 1 is cooling down! Wait " + cooldownSkill1 + " more turn(s).");
                            validTurn = false;
                        } else {
                            damage = driver.skill1();
                            cooldownSkill1 = 1; // 1-turn cooldown
                        }
                    }

                    case 2 -> {
                        if (driver.inventory.isEmpty()) {
                            System.out.println("\n❌ You have no items to use!");
                            validTurn = false;
                            break;
                        }

                        // --- Display Available Items ---
                        System.out.println("\n🎒 Available Items:");
                        int optionNum = 1;
                        HashMap<Integer, String> menuMap = new HashMap<>();

                        for (Map.Entry<String, Integer> e : driver.inventory.entrySet()) {
                            System.out.println(optionNum + ". " + e.getKey() + " (x" + e.getValue() + ")");
                            menuMap.put(optionNum, e.getKey());
                            optionNum++;
                        }

                        System.out.println(optionNum + ". Exit");
                        menuMap.put(optionNum, "Exit");

                        int chooseItem = InputHandler.getChoice("Your choice: ", 1, optionNum);
                        String chosenItem = menuMap.get(chooseItem);

                        if (chosenItem.equals("Exit")) {
                            System.out.println("❌ You cancelled using an item.");
                            validTurn = false;
                            break;
                        }

                        // --- Handle Each Item Effect ---

                        switch (chosenItem) {
                            case "RePhil" -> {
                                //checks if the item is used 1x
                                if (rePhilUsed) {
                                    System.out.println("❌ You already used RePhil once! You can’t use it again.");
                                    break;
                                }
                                driver.baseFuel += 30;
                                driver.decreaseItem("RePhil");
                                rePhilUsed = true;
                                System.out.println("⛽ RePhil used! +30 Fuel (" + driver.baseFuel + ")");
                            }

                            case "Burning Tire" -> {
                                //checks if the item is used 1x
                                if (burningTireUsed) {
                                    System.out.println("❌ You already used Burning Tire once! You can’t use it again.");
                                    break;
                                }

                                boss.fuel -= 20;
                                driver.decreaseItem("Burning Tire");
                                burningTireUsed = true;

                                System.out.printf("🔥 Burning Tire used! -20 Boss fuel (Remaining: %d)%n", boss.fuel);
                            }

                            case "Bumper Shield" -> {
                                //checks if the item is used 1x
                                if (bumperShieldUsed) {
                                    System.out.println("❌ You already used Bumper Shield once! You can’t use it again.");
                                    break;
                                }

                                shieldActive = 20;
                                driver.decreaseItem("Bumper Shield");
                                bumperShieldUsed = true;

                                System.out.println("🛡️ Shield activated! Blocks next 20 damage");
                            }

                            default -> System.out.println("❌ Invalid item choice.");
                        }


                        validTurn = false; // using item ends player turn
                    }

                    case 3 -> {
                        int fuelGain = rand.nextInt(6) + 5; // generates 5–10
                        driver.baseFuel += fuelGain;

                        System.out.println(driver.name + " takes a breather and recovers +"
                                + fuelGain + " fuel (" + driver.baseFuel + ")");

                        validTurn = true; // <-- important: turn is valid so boss will attack
                    }


                }

                // ✅ Apply skill damage after all actions
                if (validTurn && damage > 0) {
                    //Resets the limit of the items to 0
                    rePhilUsed = false;
                    burningTireUsed = false;
                    bumperShieldUsed = false;
                    //------------------
                    boss.fuel -= damage;
                    if (boss.fuel < 0) boss.fuel = 0;
                    System.out.println("💥 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
                }



                // --- Boss Turn ---
                // Added "if(!bossPassive)"----
                // for if the player choose to retry the map then cancels it the boss will not attack
                if(!bossPassive){
                    if (validTurn && boss.fuel > 0) {
                        System.out.println("\n--- Boss Turn ---");
                        int bossDamage = 0;
                        rounds++;

                        // if ultimate is ready, randomly decide to use it (50% chance)
                        if (bossUltimateCD == 0 && rand.nextInt(2) == 0) {
                            bossDamage = boss.ultimate();
                            bossUltimateCD = 5; // example: same cooldown as player's skill 3

                        }else {
                            bossDamage = boss.attackSkill(); // default: basic attack

                        }

                        // Apply shield effects

                        if (shieldActive > 0) {
                            // Determine how much damage the shield can block
                            int blocked = Math.min(shieldActive, bossDamage);
                            bossDamage -= blocked;
                            shieldActive -= blocked; // reduce shield by the blocked amount, instead of resetting to 0
                            System.out.println("🛡️ Shield blocked " + blocked + " damage! Remaining shield: " + shieldActive);
                        }


                        // Apply damage to player
                        driver.baseFuel -= bossDamage;
                        if (driver.baseFuel < 0) driver.baseFuel = 0;
                        System.out.println("🔥 Boss dealt " + bossDamage + "! Your fuel left: " + driver.baseFuel);
                    }

// --- Decrease ultimate cooldown after each turn ---
                    if (bossUltimateCD > 0) bossUltimateCD--;


                    // ✅ Cooldowns tick down after the full round (player + boss)
                    // ✅ Cooldowns tick down ONLY after a valid round (player + boss)
                    if (validTurn) {
                        if (cooldownSkill1 > 0) cooldownSkill1--;

                    }
                }else{
                    System.out.println("😐 The boss stands still and doesn’t attack...");
                }


                // --- defeat check ---
                if (driver.baseFuel <= 0) {
                    System.out.println("\n💀 Defeated by " + boss.name + "! You failed to protect the passengers...");

                    if (retryPrompt(driver, boss)) {
                        // Reset all player and mission stats
                        // 👇 Restart the entire map loop instead of continuing boss fight
                        return play(driver);
                    } else {
                        System.out.println("👋 You chose not to retry. Game Over.");
                        return false;
                    }
                }


                if (boss.fuel <= 0) {
                    System.out.println("\n✅ Boss defeated!");
                    driver.levelUp(2);

                    defeatBoss = true;
                    System.out.println("🎉 Mission Complete!");
                    System.out.println("Passengers: " + passengerList.getPassengers() +
                            " | Total ₱" + money);
                    System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                    System.out.println("🎉 You unlocked 2nd skill");
                    missionComplete = true;
                    return true;

                }
            }

        }


        return false;
    }

    private boolean retryPrompt(Driver driver, Bossing boss) {
        int retryChoice = InputHandler.getChoice("\n🔁 Try again Map 1? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = retryChoice == 1;
        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map 1 from Stop 1...");
        } else {
            bossPassive = true;
            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }

    private void resetMap(Driver driver, Bossing boss) {
        bossPassive = false;
        driver.baseFuel = 150;
        driver.inventory.clear();
        boss.fuel = 250;
        passengers = 0;
        money = 0;
    }


}