package coding.samguk;

import java.util.Arrays;
import java.util.List;

public class App {
    public void testCreateSomeGenerals() {
        General 조홍 = new General("조홍", (218-50-1),  74, 47, 75, 41, 74, 72);
        General 제갈량 = new General("제갈량", 181,     61, 100, 95, 92, 92, 78);

        List<General> generals = Arrays.asList(조홍, 제갈량);
        System.out.println(generals);
    }

    public void testCreate낙양() {
        General 조조 = new General("조조", (194-40-1), 89, 94, 98, 90, 93, 59);
        General 순욱 = new General("순욱", (194-32-1), 37, 96, 85, 85, 62, 60);

        Province 낙양 = new Province(11, "낙양", 조조, 조조, 순욱, 782_000, 3_500, 65_000, 40, 57, 0, 60, 56, 730, 40);
        System.out.println(낙양);
    }

    public void test낙양장수들() {
        GeneralsMap map = GeneralsMap.getInstance();
        General 조조 = map.find("조조");
        General 순욱 = map.find("순욱");
        Province 낙양 = new Province(11, "낙양", 조조, 조조, 순욱, 782_000, 3_500, 65_000, 40, 57, 0, 60, 56, 730, 40);
        낙양.addGeneral(map.find("조조").setArmy(6500));
        낙양.addGeneral(map.find("곽가"));
        낙양.addGeneral(map.find("하우연").setArmy(2300));
        낙양.addGeneral(map.find("하우은"));
        낙양.addGeneral(map.find("하우모").setArmy(1500));
        낙양.addGeneral(map.find("순욱").setArmy(1600));
        낙양.addGeneral(map.find("순상"));
        낙양.addGeneral(map.find("순유"));
        낙양.addGeneral(map.find("조앙").setArmy(1000));
        낙양.addGeneral(map.find("조홍").setArmy(2000));
        낙양.addGeneral(map.find("조인").setArmy(2100));
        낙양.addGeneral(map.find("조순").setArmy(1800));
        낙양.addGeneral(map.find("진교"));
        낙양.addGeneral(map.find("정욱"));
        낙양.addGeneral(map.find("전위").setArmy(2300));
        낙양.addGeneral(map.find("이전").setArmy(1800));
        낙양.addGeneral(map.find("유연"));
        System.out.println(낙양);
    }

    public void test조조능력치() {
        GeneralsMap map = GeneralsMap.getInstance();
        General 조조 = map.find("조조");
        System.out.println(조조);
    }

    public void testProvinceNet() { 
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양11 = pNet.find(11);
        Province 홍농12 = pNet.find(12);
        Province 완22 = pNet.find(22);
        Province 신야23 = pNet.find(23);
        Province 허창19 = pNet.find(19);

        //지역 정보 표시 
        System.out.println(낙양11);
        System.out.println(홍농12);
        System.out.println(완22);
        System.out.println(신야23);
        System.out.println(허창19);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Coding Samguk V0!");
        App app = new App();

        //1. 몇 명의 장수 만들어 보기 
        app.testCreateSomeGenerals();

        //2. 낙양 만들기 
        app.testCreate낙양();

        //3. 낙양 장수들(17명)
        app.test낙양장수들();

        //4. 조조의 능력치(+보물)
        app.test조조능력치();

        //5. 낙양 지도 
        app.testProvinceNet();
    }
}
