package Boss;

import Characters.Driver;

import Maps.*;
import Utils.InputHandler;
import java.util.*;

public class BossFight {
    private Bossing boss;
    private Driver driver;
    private Random rand;

    // Combat state
    private int shieldActive;
    private int cooldownSkill1, cooldownSkill2, cooldownSkill3;
    private int bossUltimateCD;
    private int rounds;
    private boolean bossPassive;
    private boolean defeatBoss;

    // Item usage tracking
    private boolean rePhilUsed;
    private boolean burningTireUsed;
    private boolean bumperShieldUsed;

    private World gameMap;

    public BossFight(Bossing boss, Driver driver, World gameMap) {
        this.boss = boss;
        this.driver = driver;
        this.gameMap = gameMap;
        this.rand = new Random();
        resetCombatState();
    }

    private void resetCombatState() {
        this.shieldActive = 0;
        this.cooldownSkill1 = 0;
        this.cooldownSkill2 = 0;
        this.cooldownSkill3 = 0;
        this.bossUltimateCD = 0;
        this.rounds = 1;
        this.bossPassive = false;
        this.defeatBoss = false;
        this.rePhilUsed = false;
        this.burningTireUsed = false;
        this.bumperShieldUsed = false;
    }

    // Return int: 1 = boss defeated, -1 = restart map, 0 = game over
    public int start() {
        resetCombatState();

        System.out.println("\n========== ⚔️ BOSS FIGHT START ==========");
        System.out.println("🚍 " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
        System.out.println("🧑‍✈️ Driver: " + driver.name + " (Fuel: " + driver.baseFuel + ")");
        System.out.println("------------------------------------------");

        // Display unlocked skills based on map
        if (gameMap instanceof Map1) {
            System.out.println("💡 Skill 1 unlocked for this map!");
        } else if (gameMap instanceof Map2) {
            System.out.println("💡 Skills 1 & 2 unlocked for this map!");
        } else if ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) {
            System.out.println("💡 All 3 Skills unlocked for this map!");
        }

        while (!defeatBoss) {
            // Display menu based on map type
            displayCombatMenu();

            int maxChoice = getMaxChoiceForMap();
            int choice = InputHandler.getChoice("Your choice: ", 0, maxChoice);

            int damage = 0;
            boolean validTurn = true;
            boolean shouldRestartMap = false;

            // Handle player action based on map type and get damage value
            ActionResult actionResult = null;
            if (gameMap instanceof Map1) {
                actionResult = handleMap1Actions(choice);
            } else if (gameMap instanceof Map2) {
                actionResult = handleMap2Actions(choice);
            } else if ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) {
                actionResult = handleMap3Actions(choice);
            }

            if (actionResult != null) {
                validTurn = actionResult.validTurn;
                damage = actionResult.damage;
                shouldRestartMap = actionResult.shouldRestartMap;
            }

            if (shouldRestartMap) {
                return -1; // Restart map
            }

            // Apply damage and reset item usage if valid turn with damage
            if (validTurn && damage > 0) {
                resetItemUsage();
                boss.fuel -= damage;
                if (boss.fuel < 0) boss.fuel = 0;
                System.out.println("💥 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
            }

            // Boss turn
            handleBossTurn(validTurn);

            // Decrement cooldowns
            decrementCooldowns(validTurn);

            // Check win/lose conditions
            int result = checkCombatStatus();
            if (result != 0) return result;
        }
        return 0;
    }

    // Helper class to return multiple values from action handlers
    private static class ActionResult {
        boolean validTurn;
        int damage;
        boolean shouldRestartMap;

        ActionResult(boolean validTurn, int damage, boolean shouldRestartMap) {
            this.validTurn = validTurn;
            this.damage = damage;
            this.shouldRestartMap = shouldRestartMap;
        }
    }

    private void displayCombatMenu() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("\n     ⚔️  ROUND " + rounds + "  ⚔️");
        System.out.println("\n--- Player Turn ---");
        System.out.println("Fuel: " + driver.baseFuel + " | Boss Fuel: " + boss.fuel);

        if (gameMap instanceof Map1) {
            System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
            System.out.println("2. Use Item");
            System.out.println("3. Skip Turn ( +(5-10) Fuel)");
            System.out.println("----------------------");
            System.out.println("0. Exit Fight (Restart Current Map)");
        } else if (gameMap instanceof Map2) {
            System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
            System.out.println("2. Use Skill 2" + (cooldownSkill2 > 0 ? " (⏳ " + cooldownSkill2 + " turns left)" : ""));
            System.out.println("3. Use Item");
            System.out.println("4. Skip Turn ( +(5-10) Fuel)");
            System.out.println("----------------------");
            System.out.println("0. Exit Fight (Restart Current Map)");
        } else if ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) {
            System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
            System.out.println("2. Use Skill 2" + (cooldownSkill2 > 0 ? " (⏳ " + cooldownSkill2 + " turns left)" : ""));
            System.out.println("3. Use Skill 3" + (cooldownSkill3 > 0 ? " (⏳ " + cooldownSkill3 + " turns left)" : ""));
            System.out.println("4. Use Item");
            System.out.println("5. Skip Turn ( +(5-10) Fuel)");
            System.out.println("----------------------");
            System.out.println("0. Exit Fight (Restart Current Map)");
        }
    }

    private int getMaxChoiceForMap() {
        if (gameMap instanceof Map1) return 3;
        else if (gameMap instanceof Map2) return 4;
        else if ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) return 5;
        return 3; // default
    }

    private ActionResult handleMap1Actions(int choice) {
        boolean validTurn = true;
        int damage = 0;
        boolean shouldRestartMap = false;

        switch (choice) {
            case 0 -> {
                if (retryPrompt()) {
                    shouldRestartMap = true;
                }
            }
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
                validTurn = handleItemUsage();
            }
            case 3 -> {
                handleSkipTurn();
            }
        }
        return new ActionResult(validTurn, damage, shouldRestartMap);
    }

    private ActionResult handleMap2Actions(int choice) {
        boolean validTurn = true;
        int damage = 0;
        boolean shouldRestartMap = false;

        switch (choice) {
            case 0 -> {
                if (retryPrompt()) {
                    shouldRestartMap = true;
                }
            }
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
                validTurn = handleItemUsage();
            }
            case 4 -> {
                handleSkipTurn();
            }
        }
        return new ActionResult(validTurn, damage, shouldRestartMap);
    }

    private ActionResult handleMap3Actions(int choice) {
        boolean validTurn = true;
        int damage = 0;
        boolean shouldRestartMap = false;

        switch (choice) {
            case 0 -> {
                if (retryPrompt()) {
                    shouldRestartMap = true;
                }
            }
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
                    cooldownSkill3 = 3;
                }
            }
            case 4 -> {
                validTurn = handleItemUsage();
            }
            case 5 -> {
                handleSkipTurn();
            }
        }
        return new ActionResult(validTurn, damage, shouldRestartMap);
    }

    private boolean handleItemUsage() {
        if (driver.inventory.isEmpty()) {
            System.out.println("\n❌ You have no items to use!");
            return false;
        }

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
            return false;
        }

        // Handle item effects with map-specific values
        int rePhilAmount = ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) ? 50 :
                (gameMap instanceof Map2) ? 40 : 30;
        int burningTireDamage = ((gameMap instanceof Map3) ||(gameMap instanceof Finale)) ? 40 :
                (gameMap instanceof Map2) ? 30 : 20;
        int shieldAmount = ((gameMap instanceof Map3) ||(gameMap instanceof Finale))? 40 :
                (gameMap instanceof Map2) ? 30 : 20;

        switch (chosenItem) {
            case "RePhil" -> {
                if (rePhilUsed) {
                    System.out.println("❌ You already used RePhil once! You can't use it again.");
                    break;
                }
                driver.baseFuel += rePhilAmount;
                driver.decreaseItem("RePhil");
                rePhilUsed = true;
                System.out.println("⛽ RePhil used! +" + rePhilAmount + " Fuel (" + driver.baseFuel + ")");
            }
            case "Burning Tire" -> {
                if (burningTireUsed) {
                    System.out.println("❌ You already used Burning Tire once! You can't use it again.");
                    break;
                }
                boss.fuel -= burningTireDamage;
                driver.decreaseItem("Burning Tire");
                burningTireUsed = true;
                System.out.printf("🔥 Burning Tire used! -" + burningTireDamage + " Boss fuel (Remaining: %d)%n", boss.fuel);
            }
            case "Bumper Shield" -> {
                if (bumperShieldUsed) {
                    System.out.println("❌ You already used Bumper Shield once! You can't use it again.");
                    break;
                }
                shieldActive = shieldAmount;
                driver.decreaseItem("Bumper Shield");
                bumperShieldUsed = true;
                System.out.println("🛡️ Shield activated! Blocks next " + shieldAmount + " damage");
            }
            default -> System.out.println("❌ Invalid item choice.");
        }
        return false;
    }

    private void handleSkipTurn() {
        int fuelGain = rand.nextInt(6) + 5;
        driver.baseFuel += fuelGain;
        System.out.println(driver.name + " takes a breather and recovers +" + fuelGain + " fuel (" + driver.baseFuel + ")");
    }

    private void handleBossTurn(boolean validTurn) {
        if (!bossPassive) {
            if (validTurn && boss.fuel > 0) {
                System.out.println("\n--- Boss Turn ---");
                int bossDamage = 0;
                rounds++;

                if (bossUltimateCD == 0 && rand.nextInt(2) == 0) {
                    bossDamage = boss.ultimate();
                    bossUltimateCD = 5;
                    System.out.println("💥 Boss unleashed its Ultimate Skill!");
                } else {
                    bossDamage = boss.attackSkill();
                    System.out.println("👊 Boss used Basic Attack!");
                }

                if (shieldActive > 0) {
                    int blocked = Math.min(shieldActive, bossDamage);
                    bossDamage -= blocked;
                    shieldActive -= blocked;
                    System.out.println("🛡️ Shield blocked " + blocked + " damage! Remaining shield: " + shieldActive);
                }

                driver.baseFuel -= bossDamage;
                if (driver.baseFuel < 0) driver.baseFuel = 0;
                System.out.println("🔥 Boss dealt " + bossDamage + "! Your fuel left: " + driver.baseFuel);
            }

            if (bossUltimateCD > 0) bossUltimateCD--;
        } else {
            System.out.println("😐 The boss stands still and doesn't attack...");
        }
    }

    private void decrementCooldowns(boolean validTurn) {
        if (validTurn) {
            if (cooldownSkill1 > 0) cooldownSkill1--;
            if (cooldownSkill2 > 0) cooldownSkill2--;
            if (cooldownSkill3 > 0) cooldownSkill3--;
        }
    }

    private void resetItemUsage() {
        rePhilUsed = false;
        burningTireUsed = false;
        bumperShieldUsed = false;
    }

    private int checkCombatStatus() {
        if (driver.baseFuel <= 0) {
            System.out.println("\n💀 Defeated by " + boss.name + "! You failed to protect the passengers...");
            if (retryPrompt()) {
                return -1; // Restart map
            } else {
                System.out.println("👋 You chose not to retry. Game Over.");
                return 0; // Game over
            }
        }

        if (boss.fuel <= 0) {
            System.out.println("\n✅ Boss defeated!");
            defeatBoss = true;
            return 1; // Boss defeated
        }
        return 0; // Continue combat
    }

    private boolean retryPrompt() {
        int retryChoice = InputHandler.getChoice("\n🔄 Retry this map? (1 = Yes, 2 = No): ", 1, 2);
        if (retryChoice == 1) {
            System.out.println("\n🔁 Restarting map from the beginning...");
            return true;
        }
        return false;
    }
}