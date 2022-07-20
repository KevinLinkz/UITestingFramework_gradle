package com.UIautomation.pageObject;

import com.UIautomation.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Keywords {
    private static volatile Keywords instance = null;

    private Keywords() {
    }

    public static Keywords getInit() {
        if (instance == null) {
            synchronized (Keywords.class) {
                if (instance == null) {
                    instance = new Keywords();
                }
            }
        }
        return instance;
    }

    public void click(By byObject) {
        WebDriverFactory.getInit().getWebDriver().findElement(byObject).click();
    }

    public void setText(By byObject, String strValue) {
        WebDriverFactory.getInit().getWebDriver().findElement(byObject).clear();
        WebDriverFactory.getInit().getWebDriver().findElement(byObject).sendKeys(strValue);
    }



}
