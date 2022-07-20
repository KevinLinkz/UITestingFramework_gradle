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
   6. Select and open first result. Verify the details (Product Name & Price) DONE
   7. Buy the product DONE
   8. Verify and enter the required details on Checkout page. DONE
   9. Verify the order status and capture the screen. DONE
*/

public class CheckOutDetailsPageObject {
    private static volatile CheckOutDetailsPageObject instance = null;

    private CheckOutDetailsPageObject() {
    }

    public static CheckOutDetailsPageObject getInit() {
        if (instance == null) {
            synchronized (CheckOutDetailsPageObject.class) {
                if (instance == null) {
                    instance = new CheckOutDetailsPageObject();
                }
            }
        }
        return instance;
    }

    By txtNameItem = By.xpath("//*[@id=\"item_5_title_link\"]/div");
    By txtPriceItem = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div");
    By btnFinish = By.xpath("//button[@id='finish'] ");


    public void verifiedNameAndPrice(String strNameItem, String strPriceItem, String strTestName) {
        String strRealNameItem = WebDriverFactory.getInit().getWebDriver().findElement(txtNameItem).getText();
        String strRealPriceItem = WebDriverFactory.getInit().getWebDriver().findElement(txtPriceItem).getText();
        Assert.assertEquals(strRealNameItem, strNameItem);
        Assert.assertEquals(strRealPriceItem, strPriceItem);
        ExtendTestFactory.getInit().setStatusTest("PASS", "Verified item, compare Name Item and Price Item", ScreenshotService.getInit().screenShot(strTestName));
        Keywords.getInit().click(btnFinish);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Last Page", ScreenshotService.getInit().screenShot(strTestName));

    }


}
