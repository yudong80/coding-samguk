package coding.samguk;

import java.util.List;

public class GeneralUtils {
    public static int getTotalWarAbility(List<General> gens) {
        int res = 0;
        for (General g : gens) {
            res += g.getWarAbility();
        }
        return res;
    }

    public static int getTotalCharisma(List<General> gens) {
        int res = 0;
        for (General g : gens) {
            res += g.getCharisma();
        }
        return res;
    }
}
