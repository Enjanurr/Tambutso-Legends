package Boss;

import java.util.Random;
public class SirKhai extends Bossing{
    Random rand = new Random();
    public SirKhai(){
     super("Sir Khai",300);
    }
    public int attackSkill(){
        System.out.println(name + " use Precision Drift");
        return rand.nextInt(26) + 10;
    }
    public int ultimate(){
        System.out.println(name + " use Full Stop");
        return rand.nextInt(36) + 35;
    }
}
