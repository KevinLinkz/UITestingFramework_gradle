package com.UIautomation.factory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Locale;

public class ExtendTestFactory {
    //I use singleton Design Pattern, so the many thread on TestNG, will make this for many times.
    //And the reason i use this design pattern, because it's more efficient more than static class.
    private static volatile ExtendTestFactory instance = null;
    private ExtendTestFactory() {

    }

    public static ExtendTestFactory getInit() {
        if (instance == null) {
            synchronized (ExtendReportSetup.class) {
                if (instance == null) {
                    instance = new ExtendTestFactory();
                }
            }
        }
        return instance;
    }

    //We use Thread Local, because there are many thread will access to this class.
    //Because we use singleton pattern, Thread local is safety variable for thread.
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    public ExtentTest getExtentTest() {
        return threadLocal.get();
    }

    public void setExtentTest(ExtentTest extentTest) {
        threadLocal.set(extentTest);
    }

    public void removeExtentTest() {
        threadLocal.remove();
    }
    public void setStatusTest(String strStatusTest, String strLog, String strPathScreenShot) {
        Status stsLog = null;
        switch (strStatusTest.toUpperCase()) {
            case "PASS":
                stsLog = Status.PASS;
                break;
            case "FAIL":
                stsLog = Status.FAIL;
                break;
            case "SKIP":
                stsLog = Status.SKIP;
                break;
            case "INFO":
                stsLog = Status.INFO;
                break;
        }

        if (strPathScreenShot != "") {
            String encodedString = changeImageToBase64(strPathScreenShot);
            getExtentTest().log(stsLog, strLog, MediaEntityBuilder.createScreenCaptureFromBase64String(encodedString).build());

        } else {
            getExtentTest().log(stsLog, strLog);
        }
    }

    //Change To Base64
    private static String changeImageToBase64(String strPathScreenShot) {
        String encodedString = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(strPathScreenShot));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedString;
    }


}
