package Maps;

import Characters.Driver;
import Utils.InputHandler;

import java.util.*;

import Boss.*;


public class Finale extends World {
    private Random rand = new Random();
    private boolean bossPassive = false;
    Bossing boss = new JolliKhai();

    public Finale() {
        super(0, 0);
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;



        while (!missionComplete) {

            int money = driver.getMoney();

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
                        System.out.println("1. RePhil (+50 Fuel) - ₱60");
                        System.out.println("2. Burning Tire (+40 dmg) - ₱60");
                        System.out.println("3. Bumper Shield (Block 40 dmg) - ₱60");
                        System.out.println("4. Back");
                        int itemChoice = InputHandler.getChoice("Choose: ", 1, 4);

                        if (itemChoice == 4) continue;

                        if (money < 60) {
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
                        money -= 60;
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

                        int resellPrice = 60;

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

            // === BOSS FIGHT ===

            int bossMaxFuel = boss.fuel;

            System.out.println("\n========== ⚔️SUPER DUPER FINAL BOSS BATTLE ==========");
            System.out.println("🚍 " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
            System.out.println("🧑‍✈️ Driver: " + driver.name + " (Your Fuel: " + driver.baseFuel + ")");
            System.out.println("------------------------------------------");
            System.out.println("💡 All 3 Skills unlocked for this map!");

            int cooldownSkill1 = 0, cooldownSkill2 = 0, cooldownSkill3 = 0;
            int shieldActive = 0, burnDamage = 0;
            boolean defeatBoss = false;
            int bossUltimateCD = 0; // cooldown tracker for boss ultimate

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
                System.out.println("2. Use Skill 2" + (cooldownSkill2 > 0 ? " (⏳ " + cooldownSkill2 + " turns left)" : ""));
                System.out.println("3. Use Skill 3" + (cooldownSkill3 > 0 ? " (⏳ " + cooldownSkill3 + " turns left)" : ""));
                System.out.println("4. Use Item");
                System.out.println("5. Skip Turn ( +(5-10) Fuel)");
                System.out.println("----------------------");
                System.out.println("6. Exit Fight(Restart Current Map)");
                int choice = InputHandler.getChoice("Your choice: ", 1, 6);

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
                                //checks if the item is used 1x
                                if (rePhilUsed) {
                                    System.out.println("❌ You already used RePhil once! You can’t use it again.");
                                    break;
                                }
                                driver.baseFuel += 50;
                                driver.decreaseItem("RePhil");
                                rePhilUsed = true;
                                System.out.println("⛽ RePhil used! +50 Fuel (" + driver.baseFuel + ")");
                            }

                            case "Burning Tire" -> {
                                //checks if the item is used 1x
                                if (burningTireUsed) {
                                    System.out.println("❌ You already used Burning Tire once! You can’t use it again.");
                                    break;
                                }

                                boss.fuel -= 40;
                                driver.decreaseItem("Burning Tire");
                                burningTireUsed = true;

                                System.out.printf("🔥 Burning Tire used! -40 Boss fuel (Remaining: %d)%n", boss.fuel);
                            }

                            case "Bumper Shield" -> {
                                //checks if the item is used 1x
                                if (bumperShieldUsed) {
                                    System.out.println("❌ You already used Bumper Shield once! You can’t use it again.");
                                    break;
                                }

                                shieldActive = 40;
                                driver.decreaseItem("Bumper Shield");
                                bumperShieldUsed = true;

                                System.out.println("🛡️ Shield activated! Blocks next 40 damage");
                            }
                            default -> System.out.println("❌ Invalid item choice.");
                        }
                        validTurn = false;
                    }
                    case 5 -> {
                        int fuelGain = rand.nextInt(6) + 5; // generates 5–10
                        driver.baseFuel += fuelGain;

                        System.out.println(driver.name + " takes a breather and recovers +"
                                + fuelGain + " fuel (" + driver.baseFuel + ")");

                        validTurn = true;
                    }
                    case 6 ->{
                        if (retryPrompt(driver, boss)) {
                            return play(driver);
                        }
                    }
                }

                // ✅ Apply skill damage only if valid turn
                if (validTurn && damage > 0) {
                    boss.fuel -= damage;
                    if (boss.fuel < 0) boss.fuel = 0;
                    //Resets the limit of the items to 0
                    rePhilUsed = false;
                    burningTireUsed = false;
                    bumperShieldUsed = false;

                    System.out.println("💥 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
                }


// --- Boss Turn ---

                if(!bossPassive){
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
                            shieldActive -= blocked;
                            System.out.println("🛡️ Shield blocked " + blocked + " damage! Remaining shield: " + shieldActive);
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

                } else{
                    System.out.println("😐 The boss stands still and doesn’t attack...");
                }


                // --- defeat check ---
                if (driver.baseFuel <= 0) {
                    System.out.println("\n💀 Defeated by " + boss.name + "! You failed to protect the passengers...");

                    if (retryPrompt(driver, boss)) {
                        return play(driver);
                    } else {
                        System.out.println("👋 You chose not to retry. Game Over.");
                        return false;
                    }
                }

                // --- victory check ---
                if (boss.fuel <= 0) {
                    Bossing boss = new JolliKhai();
                    System.out.println("✅ You defeated " + boss.name + "!");

                    //driver.levelUp(3); // level up final form

                    System.out.println("🎉 Mission Success! Finale Map Complete!");
                    System.out.println("Passengers: " + passengers + " | Total ₱" + money);
                    System.out.println("🎉 You successfully protected the passengers! Everyone is safe, thanks to your heroic driving!");
                    missionComplete = true;
                    return true; // exit Map 3 loop

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
                fuel += 50;
                driver.decreaseItem("RePhil");
                System.out.println("⛽ RePhil used! +30 fuel (" + fuel + ")");
            }
            case "Burning Tire" -> {
                boss.fuel -= 40;
                driver.decreaseItem("Burning Tire");
                System.out.println("🔥 Burning Tire used! -20 Boss fuel");
            }
            case "Bumper Shield" -> {
                shieldActive = 40;
                driver.decreaseItem("Bumper Shield");
                System.out.println("🛡️ Shield activated! Blocks 40 next damage");
            }
        }

        return true;
    }

    private boolean retryPrompt(Driver driver, Bossing boss) {
        int choice = InputHandler.getChoice("\n🔁 Try again Map 2? (1 = Yes, 2 = No): ", 1, 2);
        boolean retry = (choice == 1);

        if (retry) {
            resetMap(driver, boss);
            System.out.println("\n🔁 Restarting Map 2 from Stop 1...");
        } else {
            bossPassive = true;
            System.out.println("\n🕊️ You chose not to retry — the fight continues");
        }

        return retry;
    }
    private void resetMap(Driver driver, Bossing boss) {
        bossPassive = false;
        driver.baseFuel = 300;
        driver.inventory.clear();
        boss.fuel = 450;
        passengers = 0;
        money = 0;
    }


}
