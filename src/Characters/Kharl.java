package Characters;


import java.util.Random;

public class Kharl extends Driver {
    Random rand = new Random();

    // Base skill damage
    private int baseDamageSkill1 = 20;
    private int baseDamageSkill2 = 45;
    private int baseDamageSkill3 = 70;


    public Kharl() {
        super("Kharl",  "Quick Shift");
    }

    // ✅ Level up logic per map
    public void levelUp(int mapLevel) {
        int oldFuel = baseFuel;
        int oldSkill1 = baseDamageSkill1;
        int oldSkill2 = baseDamageSkill2;
        int oldSkill3 = baseDamageSkill3;

        switch (mapLevel) {
            case 2 -> { // after Map 1 boss
                baseDamageSkill1 = 25;  // Instead of adding why not override
                baseFuel = 150;
            }
            case 3 -> { // after Map 2 boss
                baseDamageSkill1 = 30;
                baseDamageSkill2 = 55;
                baseFuel = 250;
            }
            case 4 -> { // after Map 3 boss
                baseDamageSkill1 = 35;
                baseDamageSkill2 = 65;
                baseDamageSkill3 = 80  ;
            }
        }

// --- Print changes outside the switch ---
        System.out.println("✨ Kharl leveled up after Map " + (mapLevel - 1) + "!");
        if (baseDamageSkill1 != oldSkill1)
            System.out.println("💥 Skill1 damage increased by " + (baseDamageSkill1 - oldSkill1));
        if (baseDamageSkill2 != oldSkill2)
            System.out.println("💥 Skill2 damage increased by " + (baseDamageSkill2 - oldSkill2));
        if (baseDamageSkill3 != oldSkill3)
            System.out.println("💥 Skill3 damage increased by " + (baseDamageSkill3 - oldSkill3));
        if (baseFuel != oldFuel)
            System.out.println("⛽ Base fuel increased by " + (baseFuel - oldFuel));


    }

    // ✅ Skills that scale with base damage
    public int skill1() {
        System.out.println(name + " used Crazy Drift!");
        return rand.nextInt(11) + baseDamageSkill1; // e.g. 26–31 initially
    }

    public int skill2() {
        System.out.println(name + " used Wild Overdrive!");
        return rand.nextInt(16) + baseDamageSkill2; // e.g. 46–51 initially
    }

    public int skill3() {
        System.out.println(name + " used Final Gear!");
        return rand.nextInt(21) + baseDamageSkill3; // e.g. 76–81 initially
    }

    public void buyItem(String item){

        inventory.put(item,inventory.getOrDefault(item,0)+1);
        System.out.println("🛒 Bought " + item + "! (x" + inventory.get(item) + ")");
    }

    public void decreaseItem(String item){
        int count = inventory.getOrDefault(item,0);
        if(count>1) inventory.put(item,count-1);
        else inventory.remove(item);
    }


}




