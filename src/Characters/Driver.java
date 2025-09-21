package Characters;

public abstract class Driver {
    protected String name;
    protected int hp;
    protected int mana;
    protected int maxLevel;

    public Driver(String name, int hp, int mana, int maxLevel) {
        this.name = name;
        this.hp = hp;
        this.mana = mana;
        this.maxLevel = maxLevel;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    // Each driver will implement their own skills
    public abstract void skill1();
    public abstract void skill2();
    public abstract void skill3();
}
