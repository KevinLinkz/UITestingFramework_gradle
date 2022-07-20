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
   9. Verify the order status and capture the screen.
*/

public class CartPageObject {
    private static volatile CartPageObject instance = null;

    private CartPageObject() {
    }

    public static CartPageObject getInit() {
        if (instance == null) {
            synchronized (CartPageObject.class) {
                if (instance == null) {
                    instance = new CartPageObject();
                }
            }
        }
        return instance;
    }

    By btnAddToCart = By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']");
    By icnCart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By txtNameItemCart = By.xpath("//*[@id=\"item_5_title_link\"]/div");
    By txtPriceItemCart = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div");
    By btnCheckOut = By.xpath("//button[@id='checkout']");


    public void checkCart(String strTestName) {
        Keywords.getInit().click(btnAddToCart);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Cart Page", ScreenshotService.getInit().screenShot(strTestName));
    }

    public void checkOut(String strTestName) {
        Keywords.getInit().click(btnCheckOut);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Click Button Checkout", ScreenshotService.getInit().screenShot(strTestName));
    }

    public void verifiedCart(String strNameItem, String strPriceItem, String strTestName) {
        Keywords.getInit().click(icnCart);
        String strRealNameItem = WebDriverFactory.getInit().getWebDriver().findElement(txtNameItemCart).getText();
        String strRealPriceItem = WebDriverFactory.getInit().getWebDriver().findElement(txtPriceItemCart).getText();
        Assert.assertEquals(strRealNameItem, strNameItem);
        Assert.assertEquals(strRealPriceItem, strPriceItem);
        ExtendTestFactory.getInit().setStatusTest("PASS", "Verified item, compare Name Item and Price Item", ScreenshotService.getInit().screenShot(strTestName));

    }


}
