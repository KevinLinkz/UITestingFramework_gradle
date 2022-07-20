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

public class CheckOutPageObject {
    private static volatile CheckOutPageObject instance = null;

    private CheckOutPageObject() {
    }

    public static CheckOutPageObject getInit() {
        if (instance == null) {
            synchronized (CheckOutPageObject.class) {
                if (instance == null) {
                    instance = new CheckOutPageObject();
                }
            }
        }
        return instance;
    }

    By txtFirstName = By.xpath("//input[@id='first-name']");
    By txtLastName = By.xpath("//input[@id='last-name']");
    By txtZipPostalCode = By.xpath("//input[@id='postal-code']");
    By btnContinue = By.xpath("//input[@id='continue']");


    public void fillFormCheckOut(String strFirstName,String strLastName,String strZipPostalCode, String strTestName) {
        Keywords.getInit().setText(txtFirstName,strFirstName);
        Keywords.getInit().setText(txtLastName,strLastName);
        Keywords.getInit().setText(txtZipPostalCode,strZipPostalCode);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Checkout Page", ScreenshotService.getInit().screenShot(strTestName));
        Keywords.getInit().click(btnContinue);

    }


}
