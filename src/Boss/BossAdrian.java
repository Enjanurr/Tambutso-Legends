package Boss;
import  java.util.Random;
public class BossAdrian  extends Bossing {
    Random rand = new Random();
    public BossAdrian(){
        super("Adrian",200);
    }

    @Override
    public int attackSkill(){
        System.out.println(name + " use Engine Rush");
        return rand.nextInt(16) + 5; // 15-20
    }

    @Override
    public int ultimate(){
        System.out.println(name + " use Nitro Rage");
        return rand.nextInt(46)  + 5; // 45- 50
    }
}
