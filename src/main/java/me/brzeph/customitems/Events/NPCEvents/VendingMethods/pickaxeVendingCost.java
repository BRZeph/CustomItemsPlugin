package me.brzeph.customitems.Events.NPCEvents.VendingMethods;

public enum pickaxeVendingCost {
    T1(100),
    T2(500),
    T3(800),
    T4(1200),
    T5(1500);

    pickaxeVendingCost(int pickaxeCost) {
        this.pickaxeCost = pickaxeCost;
    }
    private final int pickaxeCost;

    public static int PickaxeVendingCost(int tier){
        return switch (tier) {
            case 1 -> T1.pickaxeCost;
            case 2 -> T2.pickaxeCost;
            case 3 -> T3.pickaxeCost;
            case 4 -> T4.pickaxeCost;
            case 5 -> T5.pickaxeCost;
            default -> throw new RuntimeException("Invalid tier provided: " + tier);
        };
    }
}
