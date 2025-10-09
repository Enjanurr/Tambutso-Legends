import Characters.*;
import Maps.*;
import Utils.InputHandler;
import Story.*;
public class Main {
    public static void main(String[] args) {
        int play = 0;
        while (play != 1) {
            play = InputHandler.getInt("Press 1 to start: ");
        }
//        Intro intro = new Intro();
//        intro.show();
        Driver driver = null;
        while (driver == null) {
            int choice = InputHandler.getInt("Choose your driver (1=Kharl, 2=Johnru, 3=James): ");
            switch (choice) {
                case 1 -> driver = new Kharl();
                case 2 -> driver = new Johnru();
                case 3 -> driver = new James();
                default -> System.out.println("❌ Invalid choice.");
            }
        }

        System.out.println("✅ You chose: " + driver.name + " (" + driver.uniqueSkill + ")");


//        Tutorials tuts = new Tutorials();
//        tuts.play(driver);

        Map1 map1 = new Map1();
        map1.play(driver);
    /*
    *    boolean successMap1 = map1.play(driver);
       boolean succesMap2 =  false;

        if (successMap1) {
           System.out.println("\n🚦 Proceeding to Map 2...");
           Map2 map2 = new Map2();
            map2.play(driver);
           succesMap2 = true;
        } else if(succesMap2){
            Map3 map3 = new Map3();
            map3.play(driver);
       }else {
            System.out.println("\n💤 You didn’t finish Map 1. Try again next time!");
        }*/

    }
}
