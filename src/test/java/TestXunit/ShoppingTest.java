package TestXunit;

import DemoXunit.Login;
import DemoXunit.Shopping;
import LoginData.ParamData;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingTest {
    Shopping shopping = new Shopping();
    Login login = new Login();

    @Test(dataProvider = "getProPrice", dataProviderClass = ParamData.class)
    public void testGetPrice(int proId, int expect) {
        int price = shopping.getPrice(proId);
        Assert.assertEquals(price, expect);
    }

    @BeforeMethod(groups = {"normal","abnormal"})
    public void login() {
//        System.out.println("login : " + Thread.currentThread());
        String res = login.userLogin("user", "pwd");

        Assert.assertEquals(res, "欢迎user");
    }

    @Test(dataProvider = "buyDataForAbnormal", dataProviderClass = ParamData.class, groups = {"abnormal"})
    public void testBuyWithAbnormal(int proId, int count, int expect) {
//        System.out.println("testBuyWithAbnormal : " + Thread.currentThread());
        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

    }

    @Test(dataProvider = "buyDataForNormal", dataProviderClass = ParamData.class, groups = {"normal", "restore"})
    public void testBuyWithNormal(int proId, int count, int expect, ITestContext context) {
//        System.out.println("testBuyWithNormal : " + Thread.currentThread());

        int pre_count = shopping.getPro(proId).getCount();
        context.setAttribute("proId", proId);
        context.setAttribute("pre_count", pre_count);

        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

        Assert.assertEquals(shopping.getPro(proId).getCount(), pre_count - count);
    }

    @Test(dataProvider = "buyDataForWithoutLogin", dataProviderClass = ParamData.class, groups = {"not_login"})
    public void testBuyWithoutLogin(int proId, int count, int expect) {
//        System.out.println("testBuyWithoutLogin : " + Thread.currentThread());
        int res = shopping.buys(proId, count);
        Assert.assertEquals(res, expect);

    }

    @AfterMethod(groups = {"normal","abnormal"})
    public void logout(ITestContext context) {
//        System.out.println("logout : " + Thread.currentThread());
        String res = login.userLogin("", "");
        Assert.assertEquals(res, "用户名或密码不能为空");
    }

    @AfterMethod(groups = {"normal"})
    public void restoreData(ITestContext context){
        int proId = (Integer) context.getAttribute("proId");
        shopping.getPro(proId).setCount((Integer) context.getAttribute("pre_count"));
    }

}
