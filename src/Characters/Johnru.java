package Characters;

import java.util.Random;

public class Johnru extends Driver {
    Random rand = new Random();

    // Base stats
    private int baseDamageSkill1 = 15;
    private int baseDamageSkill2 = 40;
    private int baseDamageSkill3 = 65;

    public int baseHeal = 5;

    public Johnru() {
        super("Johnru", "Passenger Whisperer");
    }

    // ✅ Level up logic per map
    @Override
    public void levelUp(int mapLevel) {
        int oldFuel = baseFuel;
        int oldSkill1 = baseDamageSkill1;
        int oldSkill2 = baseDamageSkill2;
        int oldSkill3 = baseDamageSkill3;

        switch (mapLevel) {
            case 2 -> { // after Map 1 boss
                baseDamageSkill1 = 20;
                baseFuel = 150;
                baseHeal = 10;
            }
            case 3 -> { // after Map 2 boss
                baseDamageSkill1 = 25;
                baseDamageSkill2 = 50;

                baseFuel = 250;
                baseHeal = 15;

            }
            case 4 -> { // after Map 3 boss
                baseDamageSkill1 = 30;
                baseDamageSkill2 = 60;
                baseDamageSkill3 = 80;
                baseFuel = 300;
                baseHeal = 20;
            }
        }

// --- Print changes outside the switch ---
        System.out.println("✨ Johnru leveled up after Map " + (mapLevel - 1) + "!");
        if (baseDamageSkill1 != oldSkill1)
            System.out.println("💥 Skill1 damage increased by " + (baseDamageSkill1 - oldSkill1));
        if (baseDamageSkill2 != oldSkill2)
            System.out.println("💥 Skill2 damage increased by " + (baseDamageSkill2 - oldSkill2));
        if (baseDamageSkill3 != oldSkill3)
            System.out.println("💥 Skill3 damage increased by " + (baseDamageSkill3 - oldSkill3));
        if (baseFuel != oldFuel)
            System.out.println("⛽ Base fuel increased by " + (baseFuel - oldFuel));
    }

    // ✅ Skill 1 now properly heals Johnru after use
    public int skill1() {
        System.out.println(name + " used Crazy Drift!");
        int damage = rand.nextInt(11) + baseDamageSkill1; // e.g., 15–25 initially
        baseFuel += baseHeal; // heal after using skill
        System.out.println("💚 Recovered " + baseHeal + " fuel! (Current Fuel: " + baseFuel + ")");
        return damage;
    }

    public int skill2() {
        System.out.println(name + " used Wild Overdrive!");
        return rand.nextInt(11) + baseDamageSkill2; // e.g., 40–50 initially
    }

    public int skill3() {
        System.out.println(name + " used Final Gear!");
        return rand.nextInt(16) + baseDamageSkill3; // e.g., 65–80 initially
    }

    // ✅ Inventory management
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
