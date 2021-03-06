package coding.samguk;

import java.util.ArrayList;
import java.util.List;

public class General {
    private String name;

    private int year;           //태어난 해
    private int warAbility;     //무력
    private int intelligence;   //지력
    private int charisma;       //매력
    private int politics;       //정치력
    private int armyCommand;    //육전지휘
    private int navalCommand;   //수전지휘
    
    private int army;           //병사수 
    private int armyType;       //병종 
    private int trained;        //훈련 
    private int morale;         //사기

    private List<Treasure> treasures = new ArrayList<>(); //보물들

    public General(String n, 
        int y, int w, int i, int c, int p,
        int ac, int nc) {
        name = n;
        year = y;
        warAbility = w;
        intelligence = i;
        charisma = c;
        politics = p;
        armyCommand = ac;
        navalCommand = nc;
    }

    public String getName() { return name; }

    public int getArmy() { return army; }

    public General setArmy(int man, int tr, int mo) {
        army = man;
        trained = tr;
        morale = mo;
        return this;
    }

    public General addTreasure(Treasure treasure) {
        treasures.add(treasure);
        return this;
    }

    private int getWarAbilityAdded() {
        int res = 0;
        for (Treasure t : treasures) {
            if (t.getWarAbilityAdded() > res) {
                res = t.getWarAbilityAdded();
            }
        }
        return res;
    }

    private int getIntelligenceAdded() {
        int res = 0;
        for (Treasure t : treasures) {
            if (t.getIntelligenceAdded() > res) {
                res = t.getIntelligenceAdded();
            }
        }
        return res;
    }

    private int getCharismaAdded() {
        int res = 0;
        for (Treasure t : treasures) {
            if (t.getCharismaAdded() > res) {
                res = t.getCharismaAdded();
            }
        }
        return res;
    }

    private int getPoliticsAdded() {
        int res = 0;
        for (Treasure t : treasures) {
            if (t.getPoliticsAdded() > res) {
                res = t.getPoliticsAdded();
            }
        }
        return res;
    }

    public int getWarAbility() { return warAbility + getWarAbilityAdded(); }

    public int getIntelligence() { return intelligence + getIntelligenceAdded(); }

    public int getCharisma() { return charisma + getCharismaAdded(); }

    public int getPolitics() { return politics + getPoliticsAdded(); }

    public int getMorale() { return morale; }

    public void motivateSoldiers(List<General> generals) {
        double total = 0;
        for (General g : generals) {
            total += g.getWarAbility();
        }

        //편의상 totalWarAbility가 100이면 20의 사기를 높인다고 가정합니다.
        int added = (int) (20 * total / 100);
        morale += added;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(year).append("::")
        .append(warAbility).append("(+").append(getWarAbilityAdded()).append("),")
        .append(intelligence).append("(+").append(getIntelligenceAdded()).append("),")
        .append(charisma).append("(+").append(getCharismaAdded()).append("),")
        .append(politics).append("(+").append(getPoliticsAdded()).append(")::")
        .append(armyCommand).append(",").append(navalCommand).append("::")
        .append(trained).append(",").append(morale);
        return sb.toString();
    }
}
