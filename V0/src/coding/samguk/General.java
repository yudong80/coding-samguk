package coding.samguk;

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

    public General setArmy(int man) {
        army = man;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(year).append("::")
        .append(warAbility).append(",").append(intelligence).append(",")
        .append(charisma).append(",").append(politics).append("::")
        .append(armyCommand).append(",").append(navalCommand);
        return sb.toString();
    }
}
