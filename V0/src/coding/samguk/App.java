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


    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Coding Samguk V0!");
        App app = new App();

        //1. 몇 명의 장수 만들어 보기 
        app.testCreateSomeGenerals();
    }
}
