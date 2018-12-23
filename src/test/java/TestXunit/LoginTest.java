package TestXunit;

import DemoXunit.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void testUserLogin1(){
        Login login = new Login();
        String ac = login.userLogin("user","pwd");
        Assert.assertEquals(ac,"欢迎user");
    }
    @Test
    public void testUserLogin2(){
        Login login = new Login();
        String ac = login.userLogin("","");
        Assert.assertEquals(ac,"用户名或密码不能为空");
    }
    @Test
    public void testUserLogin3(){
        Login login = new Login();
        String ac = login.userLogin("admin","admin");
        Assert.assertEquals(ac,"欢迎管理员");
    }
}
