package Boss;
import java.util.Random;

public class JolliKhai extends Bossing{
    Random rand = new Random();

    public JolliKhai(){
        super("Sir Khai",100);
    }
    public int attackSkill(){
        System.out.println(name + " Burger Bomb");
        return rand.nextInt(11) + 25;
    }
    public int ultimate(){
        System.out.println(name + " Sweet Victory (Ultimate)");
        return rand.nextInt(16) + 40;
    }

}
