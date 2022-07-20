package com.UIautomation.factory;

import com.UIautomation.utilities.globaldata.MyConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportSetup {
    private static volatile ExtendReportSetup instance = null;

    private ExtendReportSetup() {

    }

    public static ExtendReportSetup getInit() {
        if (instance == null) {
            //this synchronized i used because we need protect/lock for thread access.
            synchronized (ExtendReportSetup.class) {
                if (instance == null) {
                    instance = new ExtendReportSetup();
                }
            }
        }
        return instance;
    }

    public ExtentReports setUp() {
        //In my Real Framework, every time we run the automation, report will generate with Date.
        //Because this is a demo, so i will skip that.

        //Init ExtentSparkReporter
//        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(strPath);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("ReportUIAutomation.html");

        ExtentReports extentReports = new ExtentReports();

        //Put into ExtentReports
        extentReports.attachReporter(extentSparkReporter);

        //Setting config the spark
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("UI Automation Testing Report");
        extentSparkReporter.config().setReportName("UI Automation Testing Report");

        //Setting config ExtentReport
        extentReports.setSystemInfo("Execute by ", "Kevin Hertanto");
        extentReports.setSystemInfo("Execute on OS ", "UI Automation Testing V.1");

        return extentReports;

    }

}
