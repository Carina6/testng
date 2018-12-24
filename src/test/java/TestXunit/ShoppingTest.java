package TestXunit;

import DemoXunit.Login;
import DemoXunit.Shopping;
import LoginData.ParamData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShoppingTest {
    Shopping shopping = new Shopping();

    @BeforeTest
    public void testLogin() {
        Login login = new Login();
        String ac = login.userLogin("user", "pwd");
        Assert.assertEquals(ac, "欢迎user");
    }

    @Test(dataProvider = "getProPrice", dataProviderClass = ParamData.class)
    public void testGetPrice(int proId, int expect){
        int price = shopping.getPrice(proId);
        Assert.assertEquals(price, expect);
    }

    @Test(dataProvider = "buyDataUnnormal", dataProviderClass = ParamData.class)
    public void testBuyWithUnnormal(int proId, int count, int expect){
        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

    }

    @Test(dataProvider = "buyDataNormal", dataProviderClass = ParamData.class)
    public void testBuyWithNormal(int proId, int count, int expect){
        int pre_count = shopping.getPro(proId).getCount();

        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

        Assert.assertEquals(shopping.getPro(proId).getCount(), pre_count-count);
    }

}
