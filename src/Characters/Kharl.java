package Characters;
public class Kharl extends Driver{
    public Kharl(){
        super("Kharl - The Hustler", 120,60,20);
    }


    @Override
    public void skill1() {
        System.out.println("Turbo Maneuver! (Damage: 15–25, -10 Mana)");
    }

    @Override
    public void skill2() {
        System.out.println("Diskarte Talk! (Damage: 10–20, -5 Mana)");
    }

    @Override
    public void skill3() {
        System.out.println("Pogi Points! (Damage: 20–30, -15 Mana)");
    }
}
