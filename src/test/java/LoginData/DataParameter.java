package LoginData;

import DemoXunit.Products;
import org.testng.annotations.DataProvider;

public class DataParameter {

    @DataProvider
    public Object[][] getProPrice(){
        return new Object[][]{
                {Products.WEIJIN.getProId(), Products.WEIJIN.getPrice()},
                {Products.MAOZI.getProId(), Products.MAOZI.getPrice()},
                {Products.SHOUTAO.getProId(),Products.SHOUTAO.getPrice()}
        };

    }
}
