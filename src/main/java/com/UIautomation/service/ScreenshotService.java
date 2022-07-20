package com.UIautomation.service;

import com.UIautomation.factory.WebDriverFactory;
import com.UIautomation.utilities.globaldata.MyConfig;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotService {
    private static volatile ScreenshotService instance = null;
    ThreadLocal<Integer> trdIntCounterData = new ThreadLocal<>();

    private ScreenshotService() {
    }

    public static ScreenshotService getInit() {
        if (instance == null) {
            synchronized (ScreenshotService.class) {
                if (instance == null) {
                    instance = new ScreenshotService();
                }
            }
        }
        return instance;
    }

    public Integer getCounterData() {
        return trdIntCounterData.get();
    }

    public void setCounterdata(Integer intCounterData) {
        trdIntCounterData.set(intCounterData);
    }

    //Screenshot Notepad use AHK
    public String screenShot(String strFileName) {
        strFileName = strFileName + "_" + getCounterData();
        Shutterbug.shootPage(WebDriverFactory.getInit().getWebDriver(), Capture.FULL_SCROLL, 500).withName(strFileName).save(MyConfig.strPathFolderResultCap);

        setCounterdata(getCounterData() + 1);

        return MyConfig.strPathFolderResultCap + strFileName + ".png";

    }


}
