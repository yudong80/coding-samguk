package coding.samguk;

public class Treasure {
    private String name;            //보물 이름
    private int addWarAbility;      //무력 증가
    private int addIntelligence;    //지력 증가
    private int addCharisma;        //매력 증가
    private int addPolitics;        //정치력 증가
    private int addMobility;        //기동력 증가

    public Treasure(String n, int addW, int addI, int addC, int addP, int addM){
        name = n;
        addWarAbility = addW;
        addIntelligence = addI;
        addCharisma = addI;
        addPolitics = addP;
        addMobility = addM;
    }

    public String getName() { return name; }
    public int getWarAbilityAdded() { return addWarAbility; }
    public int getIntelligenceAdded() { return addIntelligence; }
    public int getCharismaAdded() { return addCharisma; }
    public int getPoliticsAdded() { return addPolitics; }
    public int getMobilityAdded() { return addMobility; }
}
