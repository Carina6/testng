package TestXunit;

import DemoXunit.Login;
import DemoXunit.Shopping;
import LoginData.ParamData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingTest {
    Shopping shopping = new Shopping();
    Login login = new Login();

    @BeforeMethod(groups = "buy-with-login")
    public void login() {
        System.out.println("login : " + System.currentTimeMillis());
        System.out.println("login : " + Thread.currentThread());
        login.userLogin("user", "pwd");
        Assert.assertTrue(Login.isLogin, "登录失败");
    }

    @Test(dataProvider = "getProPrice", dataProviderClass = ParamData.class)
    public void testGetPrice(int proId, int expect) {
        int price = shopping.getPrice(proId);
        Assert.assertEquals(price, expect);
    }

    @Test(dataProvider = "dataProvider", dataProviderClass = ParamData.class, groups = "buy-without-login")
    public void testBuyWithoutLogin(int proId, int count, int expect) {
//        logout();
        System.out.println("testBuyWithoutLogin : " + System.currentTimeMillis());
        System.out.println("testBuyWithoutLogin : " + Thread.currentThread());
        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

    }

    @Test(dataProvider = "dataProvider", dataProviderClass = ParamData.class, groups = "buy-with-login")
    public void testBuyWithAbnormal(int proId, int count, int expect) {
        System.out.println("testBuyWithAbnormal : " + System.currentTimeMillis());
        System.out.println("testBuyWithAbnormal : " + Thread.currentThread());
        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

    }

    @Test(dataProvider = "dataProvider", dataProviderClass = ParamData.class, groups = "buy-with-login")
    public void testBuyWithNormal(int proId, int count, int expect) {
        System.out.println("testBuyWithNormal : " + System.currentTimeMillis());
        System.out.println("testBuyWithNormal : " + Thread.currentThread());
        int pre_count = shopping.getPro(proId).getCount();

        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

        Assert.assertEquals(shopping.getPro(proId).getCount(), pre_count - count);
    }

    @AfterMethod(groups = "buy-with-login")
    public void logout() {
        login.userLogin("", "");
        Assert.assertFalse(Login.isLogin, "退出登录失败");
        System.out.println("logout : " + System.currentTimeMillis());
        System.out.println("logout : " + Thread.currentThread());
    }

}
