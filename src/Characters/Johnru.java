package Characters;

public class Johnru extends Driver {

    public Johnru() {
        super("Johnru – The Patience Master", 150, 50, 20);
    }

    @Override
    public void skill1() {
        System.out.println("Kalma Mode! (Damage: 5–15, -5 Mana)");
    }

    @Override
    public void skill2() {
        System.out.println("Blessing Aura! (Heal: 20–30, -15 Mana)");
    }

    @Override
    public void skill3() {
        System.out.println("Silent Drive! (Damage: 20–35, -20 Mana)");
    }
}
