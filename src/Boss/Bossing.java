package Boss;

public abstract class Bossing {

     public int fuel;
     public String name;

    public Bossing(String name, int fuel ){
         this.name = name;
         this.fuel = fuel;

    }

    public abstract int attackSkill();
    public abstract int ultimate();
}
