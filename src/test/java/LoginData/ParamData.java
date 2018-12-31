package LoginData;

import DemoXunit.Products;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ParamData {

    @DataProvider
    public Object[][] getProPrice(){
        return new Object[][]{
                // 无此商品
                {0, -1},
                //正常商品
                {Products.WEIJIN.getProId(), Products.WEIJIN.getPrice()},
                {Products.MAOZI.getProId(), Products.MAOZI.getPrice()},
                {Products.SHOUTAO.getProId(),Products.SHOUTAO.getPrice()}
        };

    }

//    private Map<String, Object[][]> dataSource(){
//        Map<String, Object[][]> dataMap = new HashMap<String, Object[][]>();
//        Object[][] o1 = new Object[][]{
//                //未登录购买
//                {2, 2, -2}
//        };
//        dataMap.put("testBuyWithoutLogin", o1);
//        Object[][] o2 = new Object[][]{
//                //无此商品
//                {0, 2, 0},
//                //购买数量小于等于0
//                {1, 0, -1},
//                //购买数量大于库存
//                {1, 1, 0}
//        };
//        dataMap.put("testBuyWithAbnormal", o2);
//        Object[][] o3 = new Object[][]{
//                //正常购买
//                {2, 2, 1},
//                {3, 1, 1},
//        };
//        dataMap.put("testBuyWithNormal", o3);
//        return dataMap;
//    }

//    @DataProvider
//    public Object[][] dataProvider(Method method){
//        return dataSource().get(method.getName());
//    }

    @DataProvider
    public Object[][] buyDataForWithoutLogin(){
        return new Object[][]{
                //未登录购买
                {2, 2, -2}
        };
    }

    @DataProvider
    public Object[][] buyDataForAbnormal(){
        return new Object[][]{
                //无此商品
                {0, 2, 0},
                //购买数量小于等于0
                {1, 0, -1},
                //购买数量大于库存
                {1, 1, 0}
        };
    }

    @DataProvider
    public Object[][] buyDataForNormal(){
        return new Object[][]{
                //正常购买
                {2, 2, 1},
                {3, 1, 1},
        };
    }

}
