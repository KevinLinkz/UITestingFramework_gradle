package com.UIautomation.pageObject;

import com.UIautomation.factory.ExtendTestFactory;
import com.UIautomation.factory.WebDriverFactory;
import com.UIautomation.service.ScreenshotService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/*TODO:
   1. Open URL www.saucedemo.com DONE
   2. Login use any accounts DONE
   3. Verify that you are logged in to the application. DONE
   4. Sort the products by the highest price
   5. Verify the result to match with your query.
   6. Select and open first result. Verify the details (Product Name & Price)
   7. Buy the product
   8. Verify and enter the required details on Checkout page.
   9. Verify the order status and capture the screen.
*/

public class LoginPageObject {
    private static volatile LoginPageObject instance = null;

    private LoginPageObject() {
    }

    public static LoginPageObject getInit() {
        if (instance == null) {
            synchronized (LoginPageObject.class) {
                if (instance == null) {
                    instance = new LoginPageObject();
                }
            }
        }
        return instance;
    }

    By txtUsername = By.xpath("//input[@id='user-name']");
    By txtPassword = By.xpath("//input[@id='password']");
    By btnLogin = By.xpath("//input[@id='login-button']");
    By spanVerifProduct = By.xpath("//span[contains(text(),'Products')]");
    By h3FailedAlert = By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]");

    public void setUp(String strUserName, String strPassword, String strTestName) {
        Keywords.getInit().setText(txtUsername, strUserName);
        Keywords.getInit().setText(txtPassword, strPassword);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Login Page", ScreenshotService.getInit().screenShot(strTestName));
    }

    public void login(String strTestName) {
        Keywords.getInit().click(btnLogin);
        ExtendTestFactory.getInit().setStatusTest("INFO", "Click Button Login", ScreenshotService.getInit().screenShot(strTestName));
    }

    public void verifiedLogin(String strValue, String strTestName) {
        String strReal = WebDriverFactory.getInit().getWebDriver().findElement(spanVerifProduct).getText();
        Assert.assertEquals(strReal, strValue);
        ExtendTestFactory.getInit().setStatusTest("PASS", "Verified login Page By existing PRODUCTS on page", ScreenshotService.getInit().screenShot(strTestName));
    }

    public void verifiedFailedLogin(String strValue, String strTestName) {
        String strReal = WebDriverFactory.getInit().getWebDriver().findElement(h3FailedAlert).getText();
        Assert.assertEquals(strReal, strValue);
        ExtendTestFactory.getInit().setStatusTest("PASS", "Verified Failed login Page By existing PRODUCTS on page", ScreenshotService.getInit().screenShot(strTestName));
    }
}