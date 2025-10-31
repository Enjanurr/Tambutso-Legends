package Maps;

import Characters.Driver;
import Utils.InputHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import Boss.*;


public class Map3 extends World {
    private Random rand = new Random();


    public Map3() {
        super(70, 20); // gaba = 70%, stops = 20
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;

        while (!missionComplete) {

            passengers = 0;
            money = 0;

            System.out.println("\n🚏 Starting Map 3: CIT-U → IT Park (" + stops + " stops)");
            System.out.println("Mission: Earn ₱1000 from 20 stops and Defeat Sir Khai.\n");

            boolean failedRun = false;

            // === STOPS PHASE ===
               for (int stop = 1; stop < stops; stop++) {
                System.out.println("\n--- Stop " + stop + " ---");
                System.out.println("Fuel: " + driver.baseFuel + " | Passengers: " + passengers + " | Money: ₱" + money);
                System.out.println("1. Pick up passengers");
                System.out.println("2. Skip stop (save fuel)");

                int action = InputHandler.getChoice("Your choice: ", 1, 2);

                if (action == 1) {
                    int fuelLoss = rand.nextInt(3) + 8 ;
                    driver.baseFuel -= fuelLoss;

                    int newPassengers = rand.nextInt(2) + 1;
                    passengers += newPassengers;

                    int fare = rand.nextInt(30) + 10;
                    money += fare;

                    System.out.println("Picked up " + newPassengers + " passengers (+₱" + fare + "), Fuel -" + fuelLoss);
                } else {
                    int fuelLoss = rand.nextInt(2) + (5);
                    driver.baseFuel -= fuelLoss;
                    System.out.println("You skipped this stop (Fuel -" + fuelLoss + ").");
                }

                if (rand.nextInt(100) < gaba) {
                    int randomGaba = rand.nextInt(5) + 1;
                    System.out.println("\n⚠️ A random event occurred! (" + randomGaba + ")");

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
                            System.out.println("🚨 LTO Stop! Pay ₱15 fine or lose 3 fuel and 1 passenger.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 15;
                            else {
                                driver.baseFuel -= 3;
                                passengers = Math.max(0, passengers - 1);
                            }
                        }
                        case 4 -> {
                            System.out.println("⛽ Fuel Leak! -4 Fuel, pay ₱10 to repair.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
                            else driver.baseFuel -= 4;
                        }
                        case 5 -> {
                            int stolen = 1;
                            System.out.println("🚐 Jeepney Thief! Lost " + stolen + " passenger. Pay ₱10 bribe to recover.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
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


            // ====================== SHOP ======================


            System.out.println("\n🎁 SHOP TIME!");
            boolean buying = true;
            while (buying) {
                System.out.println("\nYour money: ₱" + money);
                System.out.println("1. RePhil (+30 Fuel) - ₱30");
                System.out.println("2. Burning Tire (+20 dmg) - ₱30");
                System.out.println("3. Bumper Shield (Block 20 dmg) - ₱30");
                System.out.println("4. Exit Shop");
                int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);

                // 🛑 Check before proceeding
                if (money < 30 && itemChoice != 4) { // allow exit even if poor
                    System.out.println("\n💸 You don't have enough money to buy another item.");
                    System.out.println("👋 Leaving shop...");
                    break; // <-- stop loop immediately
                }

                switch (itemChoice) {
                    case 1 -> { money -= 30; driver.buyItem("RePhil");  }
                    case 2 -> { money -= 30; driver.buyItem("Burning Tire"); }
                    case 3 -> { money -= 30; driver.buyItem("Bumper Shield");  }
                    case 4 -> {
                        System.out.println("👋 Leaving shop...");
                        buying = false;
                        break;
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

            // === BOSS FIGHT ===
            Bossing boss = new SirKhai();
            int bossMaxFuel = boss.fuel;

            System.out.println("\n========== ⚔️ FINAL BOSS BATTLE ==========");
            System.out.println("🚍 " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
            System.out.println("🧑‍✈️ Driver: " + driver.name + " (Your Fuel: " + driver.baseFuel + ")");
            System.out.println("------------------------------------------");
            System.out.println("💡 All 3 Skills unlocked for this map!");

            int cooldownSkill1 = 0, cooldownSkill2 = 0, cooldownSkill3 = 0;
            int shieldActive = 0, burnDamage = 0;
            boolean defeatBoss = false;
            int bossUltimateCD = 0; // cooldown tracker for boss ultimate

            while (!defeatBoss) {
                System.out.println("\n--- Player Turn ---");
                System.out.println("Fuel: " + driver.baseFuel + " | Boss Fuel: " + boss.fuel);
                System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
                System.out.println("2. Use Skill 2" + (cooldownSkill2 > 0 ? " (⏳ " + cooldownSkill2 + " turns left)" : ""));
                System.out.println("3. Use Skill 3" + (cooldownSkill3 > 0 ? " (⏳ " + cooldownSkill3 + " turns left)" : ""));
                System.out.println("4. Use Item");
                System.out.println("5. Skip Turn (+5 Fuel)");
                int choice = InputHandler.getChoice("Your choice: ", 1, 5);

                int damage = 0;
                boolean validTurn = true;

                switch (choice) {
                    case 1 -> {
                        if (cooldownSkill1 > 0) {
                            System.out.println("⚠️ Skill 1 is cooling down! Wait " + cooldownSkill1 + " more turn(s).");
                            validTurn = false;
                        } else {
                            damage = driver.skill1();
                            cooldownSkill1 = 1;
                        }
                    }
                    case 2 -> {
                        if (cooldownSkill2 > 0) {
                            System.out.println("⚠️ Skill 2 is cooling down! Wait " + cooldownSkill2 + " more turn(s).");
                            validTurn = false;
                        } else {
                            damage = driver.skill2();
                            cooldownSkill2 = 2;
                        }
                    }
                    case 3 -> {
                        if (cooldownSkill3 > 0) {
                            System.out.println("⚠️ Skill 3 is cooling down! Wait " + cooldownSkill3 + " more turn(s).");
                            validTurn = false;
                        } else {
                            damage = driver.skill3();
                            cooldownSkill3 = 3; // ✅ fixed: cooldown for Skill 3
                        }
                    }
                    case 4 -> {
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
                                driver.baseFuel += 30;
                                driver.decreaseItem("RePhil");
                                System.out.println("⛽ RePhil used! +30 Fuel (" + driver.baseFuel + ")");
                            }
                            case "Burning Tire" -> {
                                int bonusDamage = 20;
                                boss.fuel -= bonusDamage;
                                driver.decreaseItem("Burning Tire");
                                System.out.println("🔥 Burning Tire used! -20 Boss fuel (" + boss.fuel + ")");
                            }
                            case "Bumper Shield" -> {
                                shieldActive = 20;
                                driver.decreaseItem("Bumper Shield");
                                System.out.println("🛡️ Shield activated! Blocks next 20 damage");
                            }
                            default -> System.out.println("❌ Invalid item choice.");
                        }
                        validTurn = false; // using item consumes the player's turn
                    }
                    case 5 -> {
                        driver.baseFuel += 5;
                        System.out.println(driver.name + " rests and recovers +5 fuel (" + driver.baseFuel + ")");
                        validTurn = false;
                    }
                }

                // ✅ Apply skill damage only if valid turn
                if (validTurn && damage > 0) {
                    boss.fuel -= damage;
                    if (boss.fuel < 0) boss.fuel = 0;
                    System.out.println("💥 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
                }



// --- Boss Turn ---
                if (validTurn && boss.fuel > 0) {
                    System.out.println("\n--- Boss Turn ---");
                    int bossDamage = 0;

                    // if ultimate is ready, randomly decide to use it (50% chance)
                    if (bossUltimateCD == 0 && rand.nextInt(2) == 0) {
                        bossDamage = boss.ultimate();
                        bossUltimateCD = 5; // example: same cooldown as player's skill 3
                        System.out.println("💥 Boss unleashed its Ultimate Skill!");
                    }
                    else {
                        bossDamage = boss.attackSkill(); // default: basic attack
                        System.out.println("👊 Boss used Basic Attack!");
                    }

                    // Apply shield effects
                    if (shieldActive > 0) {
                        int blocked = Math.min(shieldActive, bossDamage);
                        bossDamage -= blocked;
                        shieldActive = 0;
                        System.out.println("🛡️ Shield blocked " + blocked + " damage!");
                    }

                    // Apply damage to player
                    driver.baseFuel -= bossDamage;
                    if (driver.baseFuel < 0) driver.baseFuel = 0;
                    System.out.println("🔥 Boss dealt " + bossDamage + "! Your fuel left: " + driver.baseFuel);
                }

// --- Decrease ultimate cooldown after each turn ---
                if (bossUltimateCD > 0) bossUltimateCD--;


                // ✅ Decrement cooldowns at the end of round
               if(validTurn){
                   if (cooldownSkill1 > 0) cooldownSkill1--;
                   if (cooldownSkill2 > 0) cooldownSkill2--;
                   if (cooldownSkill3 > 0) cooldownSkill3--;
               }

                // --- defeat check ---
                if (driver.baseFuel <= 0) {
                    System.out.println("\n💀 Defeated by " + boss.name + "! You failed to protect the passengers...");

                    if (retryPrompt()) {
                        driver.baseFuel = 250;
                        boss.fuel = 100;
                        shieldActive = 0;
                        cooldownSkill1 = cooldownSkill2 = cooldownSkill3 = 0;
                        boss.fuel = 900;
                        driver.inventory.clear();
                        continue;
                    } else {
                        System.out.println("👋 You chose not to retry. Game Over.");
                        return false;
                    }
                }



                // --- victory check ---
                if (boss.fuel <= 0) {
                    System.out.println("✅ You defeated " + boss.name + "!");
                    money += 300;
                    System.out.println("💎 Reward: ₱300 | Total Money: ₱" + money);

                    driver.levelUp(3); // level up final form
                    System.out.println("🏁 Final form achieved!");
                    System.out.println("SirKhai has evolved into JolliKhai");

                    if (money >= 500) {
                        System.out.println("🎉 Mission Success! Map 2 Complete!");
                        System.out.println("Passengers: " + passengers + " | Total ₱" + money);
                        System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                        missionComplete = true;
                        return true; // exit Map 3 loop
                    } else {
                        System.out.println("\n⚠️ Boss defeated, but goal not yet reached!");
                    }



                }
            }


            // === Mission Check ===

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

    private boolean retryPrompt() {
        int choice = InputHandler.getChoice("\n🔁 Retry Map 3? (1 = Yes, 2 = No): ", 1, 2);
        return choice == 1;
    }


}
