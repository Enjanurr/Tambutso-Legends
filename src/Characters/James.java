package Characters;

public class James extends Driver {

    public James() {
        super("James – The Risk-Taker", 100, 80, 20);
    }

    @Override
    public void skill1() {
        System.out.println("Shortcut Dash! (Damage: 25–35, -15 Mana)");
    }

    @Override
    public void skill2() {
        System.out.println("Pasada Overdrive! (Damage: 20–40, -20 Mana)");
    }

    @Override
    public void skill3() {
        System.out.println("Reckless Swagger! (Damage: 30–45, -25 Mana)");
    }
}
