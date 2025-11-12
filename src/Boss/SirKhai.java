package Boss;

import java.util.Random;
public class SirKhai extends Bossing{
    Random rand = new Random();
    public SirKhai(){
     super("Sir Khai",400);
    }
    public int attackSkill(){
        System.out.println(name + " use Precision Drift");
        return rand.nextInt(6) + 35;
    }
    public int ultimate(){
        System.out.println(name + " Overdrive Authority");
        return rand.nextInt(11) + 70; // 70–80

    }
}
