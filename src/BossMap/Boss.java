package BossMap;

import Characters.Driver;
import java.util.Random;
public abstract class Boss {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int defense;

    protected Random rand = new Random();
    public Boss(String name, int hp, int defense){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.defense = defense;
    }
    public String getName(){
        return name;
    }
    public int getHp(){
        return Math.max(0, hp);
    }
    public int getDefense(){
        return defense;
    }
    public boolean isDefeated(){
        return hp <= 0;
    }
    public void takeDamage(int damage){
        hp -= damage;
        if(hp < 0)
            hp = 0;
        System.out.println(name + " took " + damage + " damage! (HP: " + getHp() + "/" + maxHp + ") ");
    }
    public void showStats(){
        System.out.println("\nBoss Encounter: " + name);
        System.out.println("Fuel (HP): " + getHp() + " | Defense: " + defense);
    }
    public abstract ActionResult performAction(Driver driver);

    public static class ActionResult{
        public int damageToPlayer;
        public boolean stunnedPlayer;
        public int accuracyDebuffTurns;
        public boolean halvesNextPlayerSkill;
        public boolean halveNextPlayerSkill;

        public ActionResult(){
            damageToPlayer = 0;
            stunnedPlayer = false;
            accuracyDebuffTurns = 0;
            halvesNextPlayerSkill = false;
        }
    }
}
