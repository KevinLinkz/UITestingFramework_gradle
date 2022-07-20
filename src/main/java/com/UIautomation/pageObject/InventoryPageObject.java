package com.UIautomation.pageObject;

import com.UIautomation.factory.ExtendTestFactory;
import com.UIautomation.factory.WebDriverFactory;
import com.UIautomation.service.ScreenshotService;
import org.openqa.selenium.By;
import org.testng.Assert;

/*TODO:
   1. Open URL www.saucedemo.com DONE
   2. Login use any accounts DONE
   3. Verify that you are logged in to the application. DONE
   4. Sort the products by the highest price DONE
   5. Verify the result to match with your query. DONE
   6. Select and open first result. Verify the details (Product Name & Price)
   7. Buy the product
   8. Verify and enter the required details on Checkout page.
   9. Verify the order status and capture the screen.
*/

public class InventoryPageObject {
    private static volatile InventoryPageObject instance = null;
    private InventoryPageObject(){}
    public static InventoryPageObject getInit(){
        if(instance == null){
            synchronized (InventoryPageObject.class){
                if(instance == null){
                    instance = new InventoryPageObject();
                }
            }
        }
        return instance;
    }

    By optHighestPrice = By.xpath("//option[contains(text(),'Price (high to low)')]");
    By txtFirstHighestPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div");
    By txtSecondHighestPrice = By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div");



    public void setOptHighestPrice(String strTestName){
        Keywords.getInit().click(optHighestPrice);
        ExtendTestFactory.getInit().setStatusTest("INFO","Highest Price",ScreenshotService.getInit().screenShot(strTestName));
    }

    public void verifiedHighestPrice(String strTestName){
        int intObject1 = Integer.parseInt( WebDriverFactory.getInit().getWebDriver().findElement(txtFirstHighestPrice).getText()
                .replace("$","")
                .replace(".",""));
        int intObject2 = Integer.parseInt( WebDriverFactory.getInit().getWebDriver().findElement(txtSecondHighestPrice).getText().
                replace("$","")
                .replace(".",""));
        Assert.assertTrue(intObject1>=intObject2);
        ExtendTestFactory.getInit().setStatusTest("PASS","Verified highest price, compare first price and second price",ScreenshotService.getInit().screenShot(strTestName));
    }
}
