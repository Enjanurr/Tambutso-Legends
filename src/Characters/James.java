package Characters;

import java.util.Random;
//======================= WHOLE REVISION =======================
public class James extends Driver {
    private final Random rand = new Random();
    private int mapLevel = 0;

    public James() {
        super("James", "Wild Overtake");
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

    // 🚗 Skill 1: Crazy Drift
    public int skill1() {
        int damage;

        switch (mapLevel) {
            case 1 -> damage = randomInRange(25, 35);
            case 2 -> damage = randomInRange(35, 45);
            case 3 -> damage = randomInRange(45, 55);
            default -> {
                System.out.println(name + " hasn’t unlocked Crazy Drift yet!");
                return 0;
            }
        }

        System.out.println(name + " used Crazy Drift! 🚗 (" + damage + " dmg)");

        return damage;
    }

    // ⚡ Skill 2: Wild Overdrive
    public int skill2() {
        if (mapLevel < 2) {
            System.out.println(name + " hasn’t unlocked Wild Overdrive yet!");
            return 0;
        }

        int damage = switch (mapLevel) {
            case 2 -> randomInRange(60, 75);
            case 3 -> randomInRange(70, 80);
            default -> 0;
        };

        System.out.println(name + " used Wild Overdrive! ⚡ (" + damage + " dmg)");
        return damage;
    }

    // 🏁 Skill 3: Final Gear
    public int skill3() {
        if (mapLevel < 3) {
            System.out.println(name + " hasn’t unlocked Final Gear yet!");
            return 0;
        }

        int damage = randomInRange(85, 95);
        //int damage = randomInRange(1000, 2000);
        System.out.println(name + " used Final Gear! 🏁 (" + damage + " dmg)");
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
