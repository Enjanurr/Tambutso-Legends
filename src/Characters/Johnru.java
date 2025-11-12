package Characters;

import java.util.Random;

//======================= WHOLE REVISION =======================
public class Johnru extends Driver {
    private final Random rand = new Random();
    private int mapLevel = 0;
    private int baseHeal = 10;

    public Johnru() {
        super("Johnru", "Passenger Whisperer");
    }

    // ✅ Update map level when progressing
    @Override
    public void levelUp(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    // ✅ Utility method for random range
    private int randomInRange(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    // 🚗 Skill 1: Smooth Maneuver
    public int skill1() {
        int damage = 0, heal = 0;

        switch (mapLevel) {
            case 1 -> {
                damage = randomInRange(15, 25);
                heal = randomInRange(1, 10);
            }
            case 2 -> {
                damage = randomInRange(20, 30);
                heal = randomInRange(5, 15);
            }
            case 3 -> {
                damage = randomInRange(25, 35);
                heal = randomInRange(10, 20);
            }
            default -> {
                System.out.println(name + " hasn’t unlocked this skill yet!");
                return 0;
            }
        }

        baseFuel += heal;
        System.out.println(name + " used Smooth Maneuver! 🚗 (" + damage + " dmg, +" + heal + " heal)");
        return damage;
    }

    // 💼 Skill 2: Fare Tactician
    public int skill2() {
        if (mapLevel < 2) {
            System.out.println(name + " hasn’t unlocked Fare Tactician yet!");
            return 0;
        }

        int damage = switch (mapLevel) {
            case 2 -> randomInRange(40, 55);
            case 3 -> randomInRange(55, 65);
            default -> 0;
        };

        System.out.println(name + " used Fare Tactician! 💼 (" + damage + " dmg)");
        return damage;
    }

    // 🗺️ Skill 3: Perfect Route
    public int skill3() {
        if (mapLevel < 3) {
            System.out.println(name + " hasn’t unlocked Perfect Route yet!");
            return 0;
        }

        int damage = randomInRange(65, 85);
        System.out.println(name + " used Perfect Route! 🗺️ (" + damage + " dmg)");
        return damage;
    }

// Inventory management
    public void buyItem(String item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        System.out.println("🛒 Bought " + item + "! (x" + inventory.get(item) + ")");
    }

    public void decreaseItem(String item) {
        int count = inventory.getOrDefault(item, 0);
        if (count > 1) inventory.put(item, count - 1);
        else inventory.remove(item);
    }
}
