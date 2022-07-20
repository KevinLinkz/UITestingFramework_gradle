package com.UIautomation.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class WebDriverFactory {
    private static volatile WebDriverFactory instance = null;

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInit() {
        if (instance == null) {
            synchronized (WebDriverFactory.class) {
                if (instance == null) {
                    instance = new WebDriverFactory();
                }
            }
        }
        return instance;
    }

    ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public WebDriver getWebDriver() {
        return threadLocal.get();
    }

    public void setWebDriver(WebDriver webDriver) {
        threadLocal.set(webDriver);
    }

    public void removeWebDriver() {
        threadLocal.remove();
    }

    public void goToURL(String strURL) {
        getWebDriver().get(strURL);
        getWebDriver().manage().window().maximize();
    }

    public void teardown() {
        getWebDriver().close();
        getWebDriver().quit();
        removeWebDriver();
    }

    public WebDriver initWebDriver(String strType) {
        switch (strType.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "OPERA":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            default:
                return null;
        }
    }

}
