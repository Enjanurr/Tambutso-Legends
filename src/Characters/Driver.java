    package Characters;

    import java.util.Random;
    import java.util.HashMap;
    public abstract class Driver {
        Random rand = new Random();
        public String name;


//        public int fuelEfficiency;  // how slowly fuel decreases
//        public int skillChance;     // chance to use skill successfully
//        public int riskLevel;       // chance of mistakes
       public String uniqueSkill;
       public int baseFuel = 100;


        //public int fuel = 100;      // starting fuel
        public int passengerCount = 0;
        public int maxPassengers = 20; // jeep capacity

        public Driver(String name, String uniqueSkill) {
            this.name = name;
        this.uniqueSkill = uniqueSkill;

        }

        public void showPassengers() {
            System.out.println(name + " has " + passengerCount + " passengers on board.");
        }


        // Abstract skills
        public abstract void levelUp(int mapLevel);
        public abstract int skill1();
        public abstract int skill2();
        public abstract int skill3();
        public abstract void buyItem(String item);
        public abstract void decreaseItem(String item);

        public HashMap<String, Integer> inventory = new HashMap<>();
        public HashMap<String, Integer> getInventory(){
            return inventory;
        }

    }
