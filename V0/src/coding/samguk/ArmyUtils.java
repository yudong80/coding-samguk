package coding.samguk;

public class ArmyUtils {
    private static final int MAX_RAISE_SOLDIERS = 15_000;
    static final double GOLD_PER_DRAFT_ARMY = 0.1;
    static final double FOOD_PER_DRAFT_ARMY = 1.0;
    static final double GOLD_PER_RAISE_ARMY = 0.4;
    static final double FOOD_PER_RAISE_ARMY = 2.0;

    //최대 징병/모병수 (자원고려X)   
    private static int getMaxRaiseSoliders(int totalAbility) {
        return (MAX_RAISE_SOLDIERS * totalAbility) / 200;
    }

    private static int roundSoliders(double input) {
        int res = (int) input;
        int under100 = res % 100;
        if (under100 > 0) {
            res = res - under100 + 100;
        }
        return res;
    }

    private static int ceilSoliders(double input) {
        int res = (int) input;
        int under100 = res % 100;
        if (under100 > 0) {
            res = res - under100;
        }
        return res;
    }

    public static int getMaxDraftSoldiers(int warAbility, int provinceGold, int provinceFood) {
        final double maxSoliders = getMaxRaiseSoliders(warAbility);
        final double maxGold = GOLD_PER_DRAFT_ARMY * maxSoliders;
        final double maxFood = FOOD_PER_DRAFT_ARMY * maxSoliders;
        if (provinceGold >= maxGold && provinceFood >= maxFood) {
            return roundSoliders(maxSoliders);
        }
        
        final double maxByGold = ((double)provinceGold) / GOLD_PER_DRAFT_ARMY;
        final double maxByFood = ((double)provinceFood) / FOOD_PER_DRAFT_ARMY;
        return roundSoliders(Math.min(maxByGold, maxByFood));
    }

    public static int getMaxRaiseSoldiers(int charisma, int provinceGold, int provinceFood) {
        final double maxSoliders = getMaxRaiseSoliders(charisma);
        final double maxGold = GOLD_PER_RAISE_ARMY * maxSoliders;
        final double maxFood = FOOD_PER_RAISE_ARMY * maxSoliders;
        if (provinceGold >= maxGold && provinceFood >= maxFood) {
            return ceilSoliders(maxSoliders);
        }
        
        final double maxByGold = ((double)provinceGold) / GOLD_PER_RAISE_ARMY;
        final double maxByFood = ((double)provinceFood) / FOOD_PER_RAISE_ARMY;
        return ceilSoliders(Math.min(maxByGold, maxByFood));
    }    
}
