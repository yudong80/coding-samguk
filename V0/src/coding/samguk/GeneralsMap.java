package coding.samguk;

import java.util.HashMap;
import java.util.Map;

public class GeneralsMap {
    private Map<String, General> map = new HashMap<>();
    private static GeneralsMap _instance;

    private GeneralsMap() {
        init();
    }

    public static GeneralsMap getInstance() { 
        if (_instance == null) {
            _instance = new GeneralsMap();
        }
        return _instance;
    }

    public General find(String key) {
        assert map.containsKey(key);
        return map.get(key);
    }

    private void init() { 
        TreasuresMap tMap = TreasuresMap.getInstance(); 

        General[] arr = {
            //이름, 연도, 무력, 지력, 매력, 정치력, 육지, 수지
            new General("조조", (194-40-1), 89, 94, 98, 90, 93, 59)
                .addTreasure(tMap.find("맹덕신서"))
                .addTreasure(tMap.find("청공검"))
                .addTreasure(tMap.find("의천검"))
                .addTreasure(tMap.find("조황비전")),
            new General("곽가", (194-25-1), 36, 80, 82, 97, 32, 6),
            new General("하우연", (194-37-1), 90, 58, 55, 82, 32, 6),
            new General("하우은", (194-28-1), 60, 62, 75, 64, 58, 27),
            new General("하우모", (194-18-1), 54, 35, 27, 40, 37, 13),
            new General("순욱", (194-32-1), 37, 96, 85, 85, 62, 60),
            new General("순상", (194-40-1), 45, 70, 76, 77, 39, 16),
            new General("순유", (194-38-1), 40, 94, 85, 94, 60, 58),
            new General("조앙", (194-20-1), 60, 54, 67, 50, 58, 19),
            new General("조홍", (194-26-1), 74, 47, 75, 41, 74, 72),
            new General("조인", (194-27-1), 83, 62, 67, 57, 79, 72),
            new General("조순", (194-25-1), 60, 51, 43, 42, 54, 20),
            new General("진교", (194-45-1), 20, 68, 71, 79, 18, 9),
            new General("정욱", (194-54-1), 25, 90, 75, 71, 85, 75),
            new General("전위", (194-35-1), 96, 33, 54, 21, 84, 74),
            new General("이전", (194-34-1), 72, 47, 53, 36, 73, 70),
            new General("유연", (194-29-1), 45, 50, 32, 57, 40, 20),
            new General("원술", (194-41-1), 79, 67, 53, 61, 71, 63),
            new General("하우돈", (194-38-1), 95, 60, 75, 54, 90, 76),
            new General("만총", (194-25-1), 38, 81, 94, 70, 72, 70),
        };

        for (General gen : arr) { 
            String key = gen.getName();
            General value = gen;
            map.put(key, value);
        }
    }

}
