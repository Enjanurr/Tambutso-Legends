package Boss;
import  java.util.Random;
public class BossAdrian  extends Bossing {
    Random rand = new Random();
    public BossAdrian(){
        super("Adrian",100);
    }
    public int attackSkill(){
        System.out.println(name + " use Engine Rush");
        return rand.nextInt(6) + 30; // 15-20
    }
    public int ultimate(){
        System.out.println(name + " use Nitro Rage");
        return rand.nextInt(6)  + 50; // 45- 50
    }

}
