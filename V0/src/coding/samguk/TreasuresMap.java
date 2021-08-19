package coding.samguk;

import java.util.HashMap;
import java.util.Map;

public class TreasuresMap {
    private Map<String, Treasure> map = new HashMap<>();
    private static TreasuresMap _instance;

    private TreasuresMap() {
        init();
    }

    public static TreasuresMap getInstance() { 
        if (_instance == null) {
            _instance = new TreasuresMap();
        }
        return _instance;
    }

    public Treasure find(String key) {
        assert map.containsKey(key);
        return map.get(key);
    }

    private void init() {
        Treasure[] arr = {
            new Treasure("맹덕신서", 0, +8, 0, +5, 0),
            new Treasure("청공검", +10, 0, 0, 0, 0),
            new Treasure("의천검", +8, 0, 0, 0, 0),
            new Treasure("조황비전", 0, 0, 0, 0, +1),
        };

        for (Treasure item : arr) { 
            String key = item.getName();
            Treasure value = item;
            map.put(key, value);
        }        
    }
    
}
