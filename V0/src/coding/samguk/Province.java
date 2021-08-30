package coding.samguk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Province {
    private int no;                 //번호
    private String name;            //이름
    private General sovereign;      //군주
    private General governor;       //태수
    private General advisor;        //군사

    private int population;         //인구
    private int gold;               //금
    private int food;               //군량
    private int loyalty;            //민충

    private int development;        //개발
    private int cultivation;        //경작
    private int waterControl;       //치수
    private int irrigation;         //관개
    private int commerce;           //상업
    private int taxRate;            //세율

    private List<General> generals = new ArrayList<>();
    private List<Province> neighbors = new ArrayList<>();

    public Province(int num, String nm, General sv, General gv, General ad, 
        int pop, int gld, int fd, int loy, 
        int dev, int cul, int wt, int irr, int cmr, int tax) {
        no = num;
        name = nm;
        sovereign = sv;
        governor = gv;
        advisor = ad;
        population = pop;
        gold = gld;
        food = fd;
        loyalty = loy;
        development = dev;
        cultivation = cul;
        waterControl = wt;
        irrigation = irr;
        commerce = cmr;
        taxRate = tax;

        //addGenerals
        addGeneralIfNotNull(sv);
        addGeneralIfNotNull(gv);
        addGeneralIfNotNull(ad);
    }

    private void addGeneralIfNotNull(General gen) {
        if (gen == null) return;

        addGeneral(gen);
    }

    public Province addGeneral(General newGeneral) {
        for (General gen : generals) {
            if (newGeneral.getName().equals(gen.getName())) {
                return this; //skip
            }
        }

        generals.add(newGeneral);
        return this;
    }

    public Province removeGeneral(General general) {
        generals.remove(general);
        return this;
    }

    public General findGeneral(String name) {
        for (General g : generals) {
            if (name.equals(g.getName())) {
                return g;
            }
        }
        return null;
    }

    public int getGenerals() {
        return generals.size();
    }

    public int getSolders() {
        int total = 0;
        for (General general : generals) {
            total += general.getArmy();
        }
        return total;
    }

    public int getNo() { return no; }

    public String getName() { return name; }

    public General getSovereign() { return sovereign; }

    public int getGold() { return gold; }

    public int getFood() { return food; }

    public Province addNeighbors(Province... others) {
        neighbors.addAll(Arrays.asList(others));    
        return this;
    }

    public void addGold(int g) { gold += g; }

    public void addFood(int f) { food += f; }

    public void transferTo(General general, Province destination) {
        if (!isTransferable(destination)) {
            String errMsg = "장수 " + general.getName() + "을 " + destination.getName() + 
                "으로 이동할 수 없습니다. \n 인접한 지역이 여부를 확인하세요!!";
            System.out.println(errMsg);
            return; 
        }

        removeGeneral(general);
        destination.addGeneral(general);
    }

    private boolean isTransferable(Province destination) {
        if (!isSameSoverign(destination)) return false;

        for (Province neighbor : neighbors) {
            //System.out.println(destination.getNo() + " vs " + neighbor.getNo());
            if (destination.getNo() == neighbor.getNo()) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameSoverign(Province destination) { 
        return sovereign == destination.getSovereign();
    }

    //TODO (군사 > 수송) 노궁, 강노, 군마 추가 필요 
    //TODO (군사 > 수송) 인접 지역외에 먼 지역도 가능 필요
    //TODO (군사 > 수송) 수행하는 장수의 무력에 따라 산적 약탈 기능 추가 필요
    public void transport(List<General> generals, Province destination, 
        int targetGold, int targetFood) {
        if (!isTransferable(destination)) {
            String errMsg = destination.getName() + 
                "으로 수송할 수 없습니다. \n 인접한 지역이 여부를 확인하세요!!";
            System.out.println(errMsg);
            return; 
        }

        if (gold < targetGold || food < targetFood) {
            String errMsg = "금 혹은 식량이 부족합니다.";
            System.out.println(errMsg);
            return;
        }

        gold -= targetGold;
        food -= targetFood; 

        destination.addGold(targetGold);
        destination.addFood(targetFood);
    }

    public void motivateSoldiers(List<General> targetGenerals) {
        for (General targetGen : targetGenerals) {
            if (generals.contains(targetGen)) {
                targetGen.motivateSoldiers(targetGenerals);
            }
        }
    }

    public int requestDraftArmy(List<General> generals) {
        assert 0 <= generals.size() && generals.size() <= 2;

        final int warAbility = GeneralUtils.getTotalWarAbility(generals);
        return ArmyUtils.getMaxDraftSoldiers(warAbility, gold, food);
    }

    public void draftArmy(int soldiers) {
        double goldCost = (double)soldiers * ArmyUtils.GOLD_PER_DRAFT_ARMY;
        double foodCost = (double)soldiers * ArmyUtils.FOOD_PER_DRAFT_ARMY;

        assert gold >= goldCost;
        assert food >= foodCost;

        gold -= (int) goldCost;
        food -= (int) foodCost;
    }

    public int requestRaiseArmy(List<General> generals) {
        assert 0 <= generals.size() && generals.size() <= 2;

        final int charisma = GeneralUtils.getTotalCharisma(generals);
        return ArmyUtils.getMaxRaiseSoldiers(charisma, gold, food);
    }

    public void raiseArmy(int soldiers) {
        double goldCost = (double)soldiers * ArmyUtils.GOLD_PER_RAISE_ARMY;
        double foodCost = (double)soldiers * ArmyUtils.FOOD_PER_RAISE_ARMY;

        assert gold >= goldCost;
        assert food >= foodCost;

        gold -= (int) goldCost;
        food -= (int) foodCost;
    }

    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        sb.append(no).append(",")
        .append(name).append(",");
        if(sovereign != null) {
            sb.append(sovereign.getName()).append("::");
        } else {
            sb.append("N/A").append("::");
        }

        if(governor != null) {
            sb.append(governor.getName()).append(",");
        } else {
            sb.append("N/A").append(",");
        }

        if (advisor != null) {
            sb.append(advisor.getName()).append("::");
        } else {
            sb.append("N/A").append("::");
        }
        
        sb.append(population).append(",").append(gold).append(",").append(food).append("::")
        .append(getSolders()).append("::")
        .append("[").append(getGenerals()).append("] ");

        for (General gen : generals) {
            sb.append(gen.getName()).append(",");
        }

        return sb.toString();
    }
}
