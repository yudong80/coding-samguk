package coding.samguk;

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
    }

    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        sb.append(no).append(",").append(name).append(",").append(sovereign.getName()).append("::")
        .append(governor.getName()).append(",").append(advisor.getName()).append("::")
        .append(population).append(",").append(gold).append(",").append(food);

        return sb.toString();
    }
}
