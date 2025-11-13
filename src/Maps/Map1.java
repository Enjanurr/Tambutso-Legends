package Maps;

import Characters.Driver;
import Utils.InputHandler;
import java.util.*;
import Boss.*;
import main.Main;


public class Map1 extends World {
    private Random rand = new Random();
    private boolean bossPassive = false; // when false, boss won't attack(for if the player chooses to retry but canceled)
    Bossing boss = new BossVaughn();


    public Map1() {
        super(30, 10);  // gaba=30%, stops=10
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;

        //Destinations
        String[] destination = {
                "Naga City Jeepney Terminal",
                "Inoburan Stop ",
                "Tinaan Crossing",
                "Langtad Stop",
                "Cantao-an Junction",
                "Tunghaan Stop (Boundary Area)",
                "Calajo-an Stop","Tulic Stop",
                "Minglanilla Public Market / Town Proper"
        };
        while (!missionComplete) {
            driver.baseFuel = 150; // base fuel incase if the player failed
            passengers = 0;
            money = 0; // change to zero after testing

            System.out.println("\n🚏 Starting Map 1: Naga to Minglanilla (" + stops + " stops)");
            System.out.println("Mission: Earn ₱300 from 10 stops and Defeat Boss Vaughn.\n");

            boolean failedRun = false;

            // for the stops uncomment this later
        for (int stop = 1; stop < stops; stop++) {

                System.out.println("\n--- Stop " + stop + " ---");
                System.out.println("Destination: "+ destination[stop - 1]);
                System.out.println("------------------------");
                System.out.println("Fuel: " + driver.baseFuel + " | Passengers: " + passengers + " | Money: ₱" + money);
                System.out.println("1. Pick up passengers");
                System.out.println("2. Skip stop (save fuel)");
                System.out.println("------------------------");
                System.out.println("0. Back to Main Menu");


                int action = InputHandler.getChoice("Your choice: ", 1, 2);
            //Added back to MENU
            if (action == 0) {
                int choice = InputHandler.getChoice("Do you want to go back to Main Menu? (1 - Yes, 2 - No): ", 1, 2);
                if (choice == 1) {
                    System.out.println("\n🔙 Returning to Main Menu...");
                    Main.main(null);
                    return false;
                } else {
                    continue;
                }
            }else if (action == 1) {
                Random rand = new Random();
                // ===========REWARD SYSTEM=========================

                 /*
                Passenger Type	    Probability
                No Passengers	    10%
                Students	        60%
                Seniors	            20%
                Office Workers	    10%
                 */
                int chance = rand.nextInt(100) + 1; // 1–100
                String passengerType = "";
                int fare = 0;



                if (chance <= 10) {
                    System.out.println("No passengers at this stop.");
                    int fuelLoss = rand.nextInt(3) + 8;
                    driver.baseFuel -= fuelLoss;

                    if (driver.baseFuel <= 0) {
                        driver.baseFuel = 0;
                        System.out.println("❌ You ran out of fuel! Game Over.");
                        failedRun = true;
                        break;
                    }

                    System.out.println("Fuel - " + fuelLoss + " | Remaining Fuel: " + driver.baseFuel);
                    continue;
                }

                if (chance <= 70) { // next 60% (11–70)
                    passengerType = "Students";
                    fare = rand.nextInt(41) + 40; // ₱40–₱70
                } else if (chance <= 90) { // next 20% (71–90)
                    passengerType = "Seniors";
                    fare = rand.nextInt(41) + 60; // ₱60–₱90
                } else { // remaining 10% (91–100)
                    passengerType = "Office Workers";
                    fare = rand.nextInt(51) + 100; // ₱100–₱140
                }


                int fuelLoss = rand.nextInt(3) + 8;
                driver.baseFuel -= fuelLoss;

                if (driver.baseFuel <= 0) {
                    driver.baseFuel = 0;
                    System.out.println("❌ You ran out of fuel! Game Over.");
                    failedRun = true;
                    break;
                }

                int newPassengers = rand.nextInt(3) + 1; // 1–3 passengers
                passengers += newPassengers;
                // NEW DESIGN
                System.out.println("\n🚖 Passenger Pickup");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("🧍 Picked Up:    " + newPassengers + " " + passengerType);
                //System.out.println("💸 Fare Earned:  ₱" + fare + "  (Base Fare: ₱" + baseFare + ")");
                System.out.println("🔋 Fuel Used:    " + fuelLoss + " | Remaining Fuel: " + driver.baseFuel);
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

                // Maximum money 1000 (final safety check)
                money += fare;
                if (money > 1000) money = 1000;

                System.out.println("💰 Total Money: ₱" + money);
                System.out.println("🚘Picked up " + newPassengers + " passengers (+₱" + fare + "), Fuel -" + fuelLoss);


                } else if(action == 2){
                    int fuelLoss = rand.nextInt(2) + (5);
                    driver.baseFuel -= fuelLoss;
                    System.out.println("You skipped this stop (Fuel -" + fuelLoss + ").");
                }
            //===========GABA SYSTEM===========
                if (rand.nextInt(100) < gaba) {
                    if (money <= 0) {
                        System.out.println("\n💸 You have no money. The gaba event was skipped!");
                        continue;
                    }
                    int randomGaba = rand.nextInt(5) + 1;
                    System.out.println("\n===============================");
                    System.out.println(" ⚠️  A RANDOM EVENT OCCURRED! ");
                    System.out.println("===============================");
                    System.out.println(" → Event: " + randomGaba + "\n");

                    switch (randomGaba) {
                        case 1 -> {
                            System.out.println("🚗 Flat Tire! -5 Fuel, pay ₱15 to fix.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 15;
                            else driver.baseFuel -= 5;
                        }
                        case 2 -> {
                            System.out.println("🔥 Engine Overheated! -10 Fuel, pay ₱10 to cool.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
                            else driver.baseFuel -= 10;
                        }
                        case 3 -> {
                            System.out.println("🚨 LTO Stop! Pay ₱20 fine or lose 3 fuel and 1 passenger.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 20;
                            else {
                                driver.baseFuel -= 3;
                                passengers = Math.max(0, passengers - 1);
                            }
                        }
                        case 4 -> {
                            System.out.println("⛽ Fuel Leak! -4 Fuel, pay ₱12 to repair.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 12;
                            else driver.baseFuel -= 4;
                        }
                        case 5 -> {
                            int stolen = 1;
                            System.out.println("🚐 Jeepney Thief! Lost " + stolen + " passenger. Pay ₱18 bribe to recover.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 18;
                            else {
                                passengers = Math.max(0, passengers - stolen);
                                driver.baseFuel -= rand.nextInt(2) + 1;
                            }
                        }
                    }


                    System.out.println("\n📊 Status Update: Passengers: " + passengers + ", Fuel: " + driver.baseFuel + ", Money: ₱" + money);
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
            if(money < 300){
                System.out.println("💸 You don’t have enough money to complete this mission.");
                System.out.println("🔁 We recommend restarting the map to try again.");
                int play = 0;
                while (play != 1) {
                    play = InputHandler.getInt("Press 1 to Continue: ");
                }
                return play(driver);
            }

            // ====================== SHOP ======================

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
                        System.out.println("1. RePhil (+30 Fuel) - ₱30");
                        System.out.println("2. Burning Tire (+20 dmg) - ₱30");
                        System.out.println("3. Bumper Shield (Block 20 dmg) - ₱30");
                        System.out.println("4. Back");
                        int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);

                        if (itemChoice == 4) continue;

                        if (money < 30) {
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
                        money -= 30;
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
                        int resellPrice = 30;

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



            // Task to do , dont make the boss attack after canceling the item


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


            while (!defeatBoss) {
                System.out.println("\n--- Player Turn ---");
                System.out.println("Fuel: " + driver.baseFuel + " | Boss Fuel: " + boss.fuel);
                System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
                System.out.println("2. Use Item");
                System.out.println("3. Skip Turn (+5 Fuel)");
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
                        driver.baseFuel += 5;
                        System.out.println(driver.name + " rests and recovers +5 fuel (" + driver.baseFuel + ")");
                        validTurn = false;
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
                    if (money >= 300) {
                        System.out.println("🎉 Mission Complete!");
                        System.out.println("Passengers: " + passengers + " | Total ₱" + money);
                        System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                        System.out.println("🎉 You unlocked 2nd skill");
                        missionComplete = true;
                        return true;
                    } else System.out.println("⚠️ Mission Incomplete!");

                }
            }

        }

        return false;
    }



    private boolean useItem(Driver driver, Bossing boss, int fuel, int shieldActive) {
        HashMap<String, Integer> inv = driver.getInventory(); // ✅ Get directly from driver

        if (inv.isEmpty()) {
            System.out.println("\n❌ You have no items to use!");
            return false;
        }

        System.out.println("\n🎒 Available Items:");
        int optionNum = 1;
        HashMap<Integer, String> menuMap = new HashMap<>();

        for (Map.Entry<String, Integer> e : inv.entrySet()) {
            System.out.println(optionNum + ". " + e.getKey() + " (x" + e.getValue() + ")");
            menuMap.put(optionNum, e.getKey());
            optionNum++;
        }

        System.out.println(optionNum + ". Cancel");
        menuMap.put(optionNum, "Cancel");

        int itemChoice = InputHandler.getChoice("Use which item? ", 1, optionNum);
        String chosenItem = menuMap.get(itemChoice);

        if (chosenItem.equals("Cancel")) {
            System.out.println("❌ Cancelled item use.");
            return false;
        }

        switch (chosenItem) {
            case "RePhil" -> {
                fuel += 30;
                driver.decreaseItem("RePhil");
                System.out.println("⛽ RePhil used! +30 fuel (" + fuel + ")");
            }
            case "Burning Tire" -> {
                boss.fuel -= 20;
                driver.decreaseItem("Burning Tire");
                System.out.println("🔥 Burning Tire used! -20 Boss fuel");
            }
            case "Bumper Shield" -> {
                shieldActive = 20;
                driver.decreaseItem("Bumper Shield");
                System.out.println("🛡️ Shield activated! Blocks 20 next damage");
            }
        }

        return true;
    }

    //Made change for the retryPrompt for easy call(to avoid spaghetti code ) for easy restart map
    private boolean retryPrompt(Driver driver, Bossing boss) {
        int choice = InputHandler.getChoice("\n🔁 Try again Map 1? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = (choice == 1);

        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map 1 from Stop 1...");
        } else {
            bossPassive = true;
            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }
    //For resetting all stats
    private void resetMap(Driver driver, Bossing boss) {
        bossPassive = false;
        driver.baseFuel = 150;
        driver.inventory.clear();
        boss.fuel = 250;
        passengers = 0;
        money = 0;
    }


}
