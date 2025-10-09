package Maps;
import Characters.Driver;
import Utils.InputHandler;
import java.util.Random;
import Boss.*;
import Shop.RandomItems;
public class Map3 extends World {
    private Random rand = new Random();
    RandomItems item = new RandomItems();
    public Map3() {
        super(70, 20); // gaba=70%, stops=10
    }

    @Override
    public boolean play(Driver driver) {
        boolean missionComplete = false;

        while (!missionComplete) {

            int fuel = 400;  // reset fuel each attempt
            passengers = 0;
            money = 0;

            System.out.println("\n🚏 Starting Map 3: CIT-U → IT Park (" + stops + " stops)");
            System.out.println("Mission: Earn ₱1000 from 20 stops and Defeat Sir Khai.\n");

            boolean failedRun = false;

            for (int stop = 1; stop < stops; stop++) {
                System.out.println("\n--- Stop " + stop + " ---");
                System.out.println("Fuel: " + fuel + " | Passengers: " + passengers + " | Money: ₱" + money);
                System.out.println("Choose an action:");
                System.out.println("1. Pick up passengers");
                System.out.println("2. Skip stop (save fuel)");

                int action = InputHandler.getChoice("Your choice: ", 1, 2);

                if (action == 1) {
                    int fuelLoss = rand.nextInt(3) + (8 - driver.fuelEfficiency); // less harsh
                    fuel -= fuelLoss;
                    System.out.println("Fuel decreased by " + fuelLoss + ". Remaining fuel: " + fuel);

                    int newPassengers = rand.nextInt(2) + 1; // 1–2 passengers
                    passengers += newPassengers;
                    System.out.println("Picked up " + newPassengers + " passengers. Total: " + passengers);

                    int fare = rand.nextInt(30) + 10; // ₱10–₱39
                    money += fare;
                    System.out.println("Earned ₱" + fare + " this stop. Total: ₱" + money);

                } else {
                    int fuelLoss = rand.nextInt(2) + (5 - driver.fuelEfficiency);
                    fuel -= fuelLoss;
                    System.out.println("Fuel decreased by " + fuelLoss + ". Remaining fuel: " + fuel);
                    System.out.println("You skipped this stop.");
                }

                // Gaba random events
                if (rand.nextInt(100) < gaba) {
                    int randomGaba = rand.nextInt(5) + 1;
                    System.out.println("\n⚠️ A random event occurred! (" + randomGaba + ")");

                    switch (randomGaba) {
                        case 1 -> {
                            System.out.println("🚗 Flat Tire! -5 Fuel, pay ₱15 to fix.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 15;
                            else fuel -= 5;
                        }
                        case 2 -> {
                            System.out.println("🔥 Engine Overheated! -10 Fuel, pay ₱10 to cool.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
                            else fuel -= 10;
                        }
                        case 3 -> {
                            System.out.println("🚨 LTO Stop! Pay ₱15 fine or lose 3 fuel and 1 passenger.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 15;
                            else {
                                fuel -= 3;
                                passengers = Math.max(0, passengers - 1);
                            }
                        }
                        case 4 -> {
                            System.out.println("⛽ Fuel Leak! -4 Fuel, pay ₱10 to repair.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
                            else fuel -= 4;
                        }
                        case 5 -> {
                            int stolen = 1;
                            System.out.println("🚐 Jeepney Thief! Lost " + stolen + " passenger. Pay ₱10 bribe to recover.");
                            int choice = InputHandler.getChoice("1 - pay, 2 - ignore:", 1, 2);
                            if (choice == 1) money -= 10;
                            else {
                                passengers = Math.max(0, passengers - stolen);
                                fuel -= rand.nextInt(2) + 1;
                            }
                        }
                    }

                    System.out.println("\n📊 Status Update: Passengers: " + passengers + ", Fuel: " + fuel + ", Money: ₱" + money);
                }

                if (fuel <= 0) {
                    System.out.println("❌ You ran out of fuel! Game Over.");
                    failedRun = true;
                    break;
                }
            }

            if (failedRun) {
                if (retryPrompt()) continue;
                else break;
            }

            // Boss Fight
            Bossing boss = new SirKhai();
            System.out.println("\n========== ⚔️ BOSS FIGHT START ==========");
            System.out.println("🚍 " + boss.name + " (Boss Fuel: " + boss.fuel + ")");
            System.out.println("🧑‍✈️ Driver: " + driver.name + " (Your Fuel: " + fuel + ")");
            System.out.println("------------------------------------------");
            System.out.println("💡 Only Skill 1 is unlocked in this map!");

//--------------------------------------  While Loop for the boss fight  -----------------------------------------------------
            boolean defeatBoss = false;

// Individual skill cooldowns
            int cooldownSkill1 = 0;
            int cooldownSkill2 = 0;
            int cooldownSkill3 = 0;

// Track active items
            int shieldActive = 0;
            int burnDamage = 0;

            while (!defeatBoss) {
                System.out.println("\n--- Player Turn ---");

                // === Show dynamic skill cooldowns ===
                System.out.println("1. Use Skill 1" + (cooldownSkill1 > 0 ? " (⏳ " + cooldownSkill1 + " turn left)" : ""));
                System.out.println("2. Use Skill 2" + (cooldownSkill2 > 0 ? " (⏳ " + cooldownSkill2 + " turn left)" : ""));
                System.out.println("3. Use Skill 3" + (cooldownSkill3 > 0 ? " (⏳ " + cooldownSkill3 + " turns left)" : ""));
                System.out.println("4. Skip turn (recover 5 fuel)");

                int choice = InputHandler.getChoice("Your action: ", 1, 4);

                int damage = 0;
                boolean skillUsed = false;
                boolean validTurn = true;  // Prevents skipping boss turn if invalid choice

                switch (choice) {
                    case 1 -> {
                        if (cooldownSkill1 > 0) {
                            System.out.println("⚠️ Skill 1 is cooling down! Wait " + cooldownSkill1 + " more turn(s).");
                            validTurn = false;
                            break;
                        }else{
                            damage = driver.skill1();
                            skillUsed = true;
                            cooldownSkill1 = 1; // 1-turn cooldown
                        }

                    }
                    case 2 -> {
                        if (cooldownSkill2 > 0) {
                            System.out.println("⚠️ Skill 2 is cooling down! Wait " + cooldownSkill2 + " more turn(s).");
                            validTurn = false;
                            break;
                        }else{
                            damage = driver.skill2();
                            skillUsed = true;
                            cooldownSkill2 = 2; // 1-turn cooldown
                        }

                    }
                    case 3 -> {
                        if (cooldownSkill3 > 0) {
                            System.out.println("⚠️ Skill 3 is cooling down! Wait " + cooldownSkill3 + " more turn(s).");
                            validTurn = false;
                            break;
                        }else{
                            damage = driver.skill3();
                            skillUsed = true;
                            cooldownSkill3 = 3; // 3-turn cooldown
                        }

                    }
                    case 4 -> {
                        fuel += 5;
                        System.out.println(driver.name + " rests and recovers 5 fuel! (" + fuel + ")");

                    }
                }

                // If player tried to use a cooling skill, restart the loop (no boss turn)
                if (!validTurn) {
                    continue;
                }

                // --- Random Item Trigger ---
                if (rand.nextInt(100) < gaba) {
                    System.out.println("\n🎁 An Item appeared! You can use it to help fight the boss.");
                    System.out.println("1. RePhil (Refill Fuel), Cost: ₱30");
                    System.out.println("2. Burning Tire (Extra Damage), Cost: ₱30");
                    System.out.println("3. Bumper Shield (Block Damage), Cost: ₱30");
                    System.out.println("4. Ignore");

                    int itemChoice = InputHandler.getChoice("Your choice: ", 1, 4);
                    switch (itemChoice) {
                        case 1 -> {
                            int fuelGain = item.rePhill();
                            fuel += fuelGain;
                            money -= 30;
                            System.out.println("\n⛽ You refilled your tank and restored " + fuelGain + " fuel!");
                            System.out.println("💰 Money left: ₱" + money + " | Current Fuel: " + fuel);
                        }
                        case 2 -> {
                            burnDamage = item.burningTire();
                            money -= 30;
                            System.out.println("\n🔥 Burning Tire ready! Deals " + burnDamage + " extra damage to the boss.");
                            System.out.println("💰 Money left: ₱" + money);
                            if (skillUsed) {
                                damage += burnDamage;
                                System.out.println("\n💥 Extra damage applied immediately! Total this turn: " + damage);
                            }
                        }
                        case 3 -> {
                            shieldActive = item.bumperShield();
                            money -= 30;
                            System.out.println("🛡️ Bumper Shield activated! Will block up to " + shieldActive + " damage this turn.");
                            System.out.println("💰 Money left: ₱" + money);
                        }
                        case 4 -> System.out.println("You ignored the item.");
                    }
                }

                // --- Player Attack Phase ---
                if (damage > 0) {
                    boss.fuel -= damage;
                    if (boss.fuel < 0) boss.fuel = 0;
                    System.out.println("💥 You dealt " + damage + " damage! Boss fuel left: " + boss.fuel);
                }

                // --- Boss Turn --- && skillUsed
                if (boss.fuel > 0 ) {
                    System.out.println("\n--- Boss Turn ---");
                    int bossDamage = (rand.nextInt(2) == 0) ? boss.attackSkill() : boss.ultimate();

                    if (shieldActive > 0) {
                        int blocked = Math.min(shieldActive, bossDamage);
                        bossDamage -= blocked;
                        shieldActive = 0;
                        System.out.println("🛡️ Shield blocked " + blocked + " damage!");
                    }

                    fuel -= bossDamage;
                    if (fuel < 0) fuel = 0;
                    System.out.println("🔥 " + boss.name + " dealt " + bossDamage + " damage! Your fuel left: " + fuel);
                }

                // ✅ Reduce cooldowns AFTER both player and boss acted
                if (cooldownSkill1 > 0) cooldownSkill1--;
                if (cooldownSkill2 > 0) cooldownSkill2--;
                if (cooldownSkill3 > 0) cooldownSkill3--;

                // --- Check defeat conditions ---
                if (fuel <= 0) {
                    System.out.println("\n💀 You were defeated by " + boss.name + "! Game over...");
                    if (retryPrompt()) {
                        defeatBoss = true;
                        continue;
                    } else return false;
                }

                if (boss.fuel <= 0) {
                    System.out.println("\n✅ You defeated " + boss.name + "!");
                    money += 150;
                    System.out.println("💎 Reward: ₱150 for victory! Total Money: ₱" + money);
                    defeatBoss = true;
                }
            }


            if (money >= 1000) {
                System.out.println("🎉 Mission Success! You deliver you passengers Safely.");
                System.out.println("You can claim you  1 bucket of jollibee.");
                // Mission check
                System.out.println("\n--- Map 2 Complete! ---");
                System.out.println("Passengers carried: " + passengers);
                System.out.println("Total Money Earned: ₱" + money);

                missionComplete = true;
                return true;
            } else {
                System.out.println("Mission: Earn ₱1000 from 20 stops and Defeat Sir Khai.\n");
                System.out.println("⚠️ Mission Incomplete. You can try the route again!");
                System.out.println("\n--- Map 1 Incomplete! ---");
                System.out.println("Passengers carried: " + passengers);
                System.out.println("Total Money Earned: ₱" + money);
            }
        }
        return false;
    }

    private boolean retryPrompt() {
        int choice = InputHandler.getChoice("\n🔁 Try again Map 1? (1 = Yes, 2 = No): ", 1, 2);
        return choice == 1;
    }
}
