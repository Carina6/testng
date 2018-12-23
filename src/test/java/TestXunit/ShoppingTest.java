package TestXunit;

import DemoXunit.Shopping;
import LoginData.DataParameter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTest {
    Shopping shopping = new Shopping();

    @Test(dataProvider = "getProPrice", dataProviderClass = DataParameter.class)
    public void testGetPrice(int proId, int expect){
        int price = shopping.getPrice(proId);
        Assert.assertEquals(price, expect);
    }

}
