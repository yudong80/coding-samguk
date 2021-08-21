package coding.samguk;

import java.util.HashMap;
import java.util.Map;

public class ProvinceNet {
    private Map<Integer, Province> map = new HashMap<>();
    private static ProvinceNet _instance;

    private ProvinceNet() {
        init();
        setNetwork();
    }

    public static ProvinceNet getInstance() { 
        if (_instance == null) {
            _instance = new ProvinceNet();
        }
        return _instance;
    }

    public Province find(int key) {
        assert map.containsKey(key);
        return map.get(key);
    }

    private void setNetwork() {
        Province 낙양 = find(11);
        Province 홍농 = find(12);
        Province 완 = find(22);
        Province 신야 = find(23);
        Province 허창 = find(19);

        낙양.addNeighbors(홍농, 완, 허창);
        홍농.addNeighbors(허창);
        완.addNeighbors(낙양, 허창);
        신야.addNeighbors(완);
        허창.addNeighbors(낙양, 완);
    }

    private void init() { 
        GeneralsMap gMap = GeneralsMap.getInstance();
        General 조조 = gMap.find("조조");
        General 순욱 = gMap.find("순욱");
        General 하우돈 = gMap.find("하우돈");
        General 만총 = gMap.find("만총");
        General 원술 = gMap.find("원술");
        Province[] arr = {
            //번호, 이름, 군주, 태수, 군사, 인구, 금, 군량, 민충, 개발, 경작, 치수, 관개, 상업, 세율
            new Province(11, "낙양", 조조, 조조, 순욱, 782_000, 3_500, 65_000, 40, 57, 0, 60, 56, 730, 40)
                .addGeneral(gMap.find("조조").setArmy(6500))
                .addGeneral(gMap.find("곽가"))
                .addGeneral(gMap.find("하우연").setArmy(2300))
                .addGeneral(gMap.find("하우은"))
                .addGeneral(gMap.find("하우모").setArmy(1500))
                .addGeneral(gMap.find("순욱").setArmy(1600))
                .addGeneral(gMap.find("순상"))
                .addGeneral(gMap.find("순유"))
                .addGeneral(gMap.find("조앙").setArmy(1000))
                .addGeneral(gMap.find("조홍").setArmy(2000))
                .addGeneral(gMap.find("조인").setArmy(2100))
                .addGeneral(gMap.find("조순").setArmy(1800))
                .addGeneral(gMap.find("진교"))
                .addGeneral(gMap.find("정욱"))
                .addGeneral(gMap.find("전위").setArmy(2300))
                .addGeneral(gMap.find("이전").setArmy(1800))
                .addGeneral(gMap.find("유연")),
            new Province(12, "홍농", 조조, 하우돈, 만총, 141_000, 2_700, 55_000, 55, 55, 0, 55, 42, 580, 40), 
            new Province(22, "완", 원술, 원술, null, 404_000, 2_450, 50_000, 78, 55, 0, 45, 42, 575, 40),
            new Province(23, "신야", null, null, null, 378_000, 150, 550, 60, 53, 0, 50, 47, 470, 40),
            new Province(19, "허창", null, null, null, 514_000, 150, 550, 50, 50, 0, 40, 30, 720, 40),
        };

        for (Province gen : arr) { 
            int key = gen.getNo();
            Province value = gen;
            map.put(key, value);
        }
    }
}
