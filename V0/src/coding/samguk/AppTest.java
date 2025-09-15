package coding.samguk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AppTest {
    @BeforeEach
    void initForTesting() {
        ProvinceNet.initForTesting();
    }

    @Test 
    void testCreateSomeGenerals() {
        General 조홍 = new General("조홍", (218-50-1),  74, 47, 75, 41, 74, 72);
        General 제갈량 = new General("제갈량", 181,     61, 100, 95, 92, 92, 78);

        //조홍의 이름과 제갈량의 지력을 확인
        assertEquals("조홍", 조홍.getName());
        assertEquals(100, 제갈량.getIntelligence());
    }
    
    @Test
    void testCreate낙양() {
        General 조조 = new General("조조", (194-40-1), 89, 94, 98, 90, 93, 59);
        General 순욱 = new General("순욱", (194-32-1), 37, 96, 85, 85, 62, 60);
        Province 낙양 = new Province(11, "낙양", 조조, 조조, 순욱, 782_000, 3_500, 65_000, 40, 57, 0, 60, 56, 730, 40);
        
        //낙양이 11번인지 확인 
        assertEquals(11, 낙양.getNo());
    }

    @Test
    void test낙양장수들() {
        GeneralsMap map = GeneralsMap.getInstance();
        General 조조 = map.find("조조");
        General 순욱 = map.find("순욱");
        Province 낙양 = new Province(11, "낙양", 조조, 조조, 순욱, 782_000, 3_500, 65_000, 40, 57, 0, 60, 56, 730, 40);
        낙양.addGeneral(map.find("조조").setArmy(6500, 65, 60));
        낙양.addGeneral(map.find("곽가"));
        낙양.addGeneral(map.find("하우연").setArmy(2300, 65, 60));
        낙양.addGeneral(map.find("하우은"));
        낙양.addGeneral(map.find("하우모").setArmy(1500, 65, 60));
        낙양.addGeneral(map.find("순욱").setArmy(1600, 65, 60));
        낙양.addGeneral(map.find("순상"));
        낙양.addGeneral(map.find("순유"));
        낙양.addGeneral(map.find("조앙").setArmy(1000, 65, 60));
        낙양.addGeneral(map.find("조홍").setArmy(2000, 65, 60));
        낙양.addGeneral(map.find("조인").setArmy(2100, 65, 60));
        낙양.addGeneral(map.find("조순").setArmy(1800, 65, 60));
        낙양.addGeneral(map.find("진교"));
        낙양.addGeneral(map.find("정욱"));
        낙양.addGeneral(map.find("전위").setArmy(2300, 65, 60));
        낙양.addGeneral(map.find("이전").setArmy(1800, 65, 60));
        낙양.addGeneral(map.find("유연"));

        //등록한 17명의 장수들 
        assertEquals(17, 낙양.getGenerals());
    }

    @Test
    void test조조능력치() {
        GeneralsMap map = GeneralsMap.getInstance();
        General 조조 = map.find("조조");

        //조조의 무력 89(+10) 확인 
        assertEquals(99, 조조.getWarAbility());
    }

    @Test
    public void testProvinceNet() {
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양11 = pNet.find(11);
        Province 홍농12 = pNet.find(12);
        Province 완22 = pNet.find(22);
        Province 신야23 = pNet.find(23);
        Province 허창19 = pNet.find(19);

        //각 지역의 정보 확인 
        assertEquals(11, 낙양11.getNo());
        assertEquals(12, 홍농12.getNo());
        assertEquals(22, 완22.getNo());
        assertEquals(23, 신야23.getNo());
        assertEquals(19, 허창19.getNo());
    }

    @Test 
    void test군사_이동() {
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양 = pNet.find(11);
        Province 홍농 = pNet.find(12);
        GeneralsMap gMap = GeneralsMap.getInstance();
        General 우금 = gMap.find("우금");

        //우금(1500)을 홍농(12) -> 낙양(11) 로 이동 
        //1. 우금이 홍농에 있음
        General 홍농의우금 = 홍농.findGeneral("우금");
        assertNotNull(홍농의우금);
        //2. 홍농의 우금의 군사 1500명 
        assertEquals(1500, 홍농의우금.getArmy());

        //3. 낙양으로 이동 
        int 낙양의군사 = 낙양.getSolders();
        홍농.transferTo(우금, 낙양);

        //4. 우금이 낙양에 있음 
        General 낙양의우금 = 낙양.findGeneral("우금");
        assertNotNull(낙양의우금);

        //5. 이동후 군사수 확인
        assertEquals(1500, 낙양.getSolders() - 낙양의군사);
    }

    @Test
    void test군사_수송() {
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양 = pNet.find(11);
        Province 홍농 = pNet.find(12);
        GeneralsMap gMap = GeneralsMap.getInstance();
        General 악진 = gMap.find("악진");
        General 강서 = gMap.find("강서");
        
        //홍농(12)의 금 1500과 군량 30000을 낙양(11)으로 수송 
        //수송은 악진, 강서가 담당 
        int 이전금 = 낙양.getGold();
        int 이전군량 = 낙양.getFood();

        홍농.transport(Arrays.asList(악진, 강서), 낙양, 1_500, 30_000);

        assertEquals(1_500, 낙양.getGold() - 이전금);
        assertEquals(30_000, 낙양.getFood() - 이전군량);
    }

    @Test
    void test군사_전쟁준비() {
        //낙양(11)의 조조(무:89) 와 하우연(무:99)이 전쟁준비를 수행합니다.
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양 = pNet.find(11);
        General 조조 = 낙양.findGeneral("조조");
        General 하우연 = 낙양.findGeneral("하우연");

        int 조조이전사기 = 조조.getMorale();
        int 하우연이전사기 = 하우연.getMorale();

        낙양.motivateSoldiers(Arrays.asList(조조, 하우연));

        assertTrue(조조.getMorale() > 조조이전사기);
        assertTrue(하우연.getMorale() > 하우연이전사기);
    }

    @Test
    void test군사_징병() { 
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양 = pNet.find(11);
        General 조조 = 낙양.findGeneral("조조");
        General 하우연 = 낙양.findGeneral("하우연");

        int army = 낙양.requestDraftArmy(Arrays.asList(조조, 하우연));
        낙양.draftArmy(army);

        assertEquals(1_4200, army);
        assertEquals(2_080, 낙양.getGold()); //3500 -> 2080
        assertEquals(50_800, 낙양.getFood()); //65000 -> 50800
    }

    @Disabled
    @Test
    void test군사_모병() { 
        ProvinceNet pNet = ProvinceNet.getInstance();
        Province 낙양 = pNet.find(11);
        General 조조 = 낙양.findGeneral("조조");
        General 곽가 = 낙양.findGeneral("곽가");

        int army = 낙양.requestRaiseArmy(Arrays.asList(조조, 곽가));
        낙양.raiseArmy(army);

        assertEquals(8_700, army);
        assertEquals(20, 낙양.getGold()); //3500 -> 20
        assertEquals(47_600, 낙양.getFood()); //65000 -> 47600
    }
}
