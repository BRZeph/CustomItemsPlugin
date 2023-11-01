package me.brzeph.customitems.MiningEvents;

import java.util.Random;

public class RandomValueGenerators {
    public static int doubleOreRandomRoll(){
        Random random = new Random();

        return random.nextInt(100) + 1;
    }
    public static int tripleOreRandomRoll(){
        Random random = new Random();

        return random.nextInt(100) + 1;
    }
    public static int gemFindRandomRollForEnchantment(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public static int gemFindRandomRollForGems(){
        Random random = new Random();
        int minValue = 30;
        int maxValue = 201;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int treasureFindRandomRoll(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public static int t1OreXPGenerator(){
        Random random = new Random();
        int minValue = 80;
        int maxValue = 150;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int t2OreXPGenerator(){
        Random random = new Random();
        int minValue = 1600;
        int maxValue = 3000;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int t3OreXPGenerator(){
        Random random = new Random();
        int minValue = 3200;
        int maxValue = 6000;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int t4OreXPGenerator(){
        Random random = new Random();
        int minValue = 4800;
        int maxValue = 9000;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int t5OreXPGenerator(){
        Random random = new Random();
        int minValue = 6400;
        int maxValue = 12000;

        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
    public static int miningRandomRoll(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    public static int durabilityEnchantmentRandomRoll(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
}
