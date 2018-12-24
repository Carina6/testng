package TestXunit;

import DemoXunit.Login;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
//    @BeforeTest
    @Test(groups = {"normal"})
    public void testUserLogin1() {
        Login login = new Login();
        String ac = login.userLogin("user", "pwd");
        Assert.assertEquals(ac, "欢迎user");
    }

    @Test(groups = {"unnormal"})
    public void testUserLogin2() {
        Login login = new Login();
        String ac = login.userLogin("", "");
        Assert.assertEquals(ac, "用户名或密码不能为空");
    }

    @Test(groups = {"admin"})
    public void testUserLogin3() {
        Login login = new Login();
        String ac = login.userLogin("admin", "admin");
        Assert.assertEquals(ac, "欢迎管理员");
    }

    @Test(enabled = false, groups = {"normal"}, parameters = {"name", "pwd", "expect"})
    public void testUserLogin4(String name, String pwd, String expect) {
        Login login = new Login();
        String ac = login.userLogin(name, pwd);
        Assert.assertEquals(ac, expect);
    }
}
