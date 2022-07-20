import com.UIautomation.factory.ExtendTestFactory;
import com.UIautomation.factory.WebDriverFactory;
import com.UIautomation.listener.TestNGListener;
import com.UIautomation.pageObject.*;
import com.UIautomation.service.ScreenshotService;
import com.UIautomation.utilities.DataProviderTesting;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

//Use the listener, for Action before test, action when test start, action if test passed,failed, etc on class TestNGListner.
@Listeners(TestNGListener.class)
public class TestChangesV1 {


    @Test(description = "This is positive scenario",
            dataProvider="ParamForTest1",
            dataProviderClass = DataProviderTesting.class)
    public void Test1(Map<String,String> mapParamsData){
        System.out.println("xx");
        ScreenshotService.getInit().setCounterdata(1);
        String strTestName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.printf(strTestName);

        WebDriverFactory.getInit().goToURL(mapParamsData.get("URL"));
        LoginPageObject.getInit().setUp(mapParamsData.get("UserName"),mapParamsData.get("Password"),strTestName);
        LoginPageObject.getInit().login(strTestName);
        LoginPageObject.getInit().verifiedLogin("PRODUCTS",strTestName);

        InventoryPageObject.getInit().setOptHighestPrice(strTestName);
        InventoryPageObject.getInit().verifiedHighestPrice(strTestName);

        InventoryItemPageObject.getInit().selectFirstItem(strTestName);
        InventoryItemPageObject.getInit().verifiedNameAndPrice(mapParamsData.get("ExpectedItem"),mapParamsData.get("ExpectedPrice"),strTestName);

        CartPageObject.getInit().checkCart(strTestName);
        CartPageObject.getInit().verifiedCart(mapParamsData.get("ExpectedItem"),mapParamsData.get("ExpectedPrice"),strTestName);
        CartPageObject.getInit().checkOut(strTestName);

        CheckOutPageObject.getInit().fillFormCheckOut(mapParamsData.get("FirstName"),mapParamsData.get("LastName"),mapParamsData.get("ZipPostalCode"),strTestName);

        CheckOutDetailsPageObject.getInit().verifiedNameAndPrice(mapParamsData.get("ExpectedItem"),mapParamsData.get("ExpectedPrice"),strTestName);

    }

    @Test(description = "This is negative scenario",
            dataProvider="ParamForTest2",
            dataProviderClass = DataProviderTesting.class)
    public void Test2(Map<String,String> mapParamsData){
        ScreenshotService.getInit().setCounterdata(1);
        String strTestName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.printf(strTestName);

        WebDriverFactory.getInit().goToURL(mapParamsData.get("URL"));

        LoginPageObject.getInit().setUp(mapParamsData.get("UserName"),mapParamsData.get("Password"),strTestName);
        LoginPageObject.getInit().login(strTestName);
        LoginPageObject.getInit().verifiedFailedLogin("Epic sadface: Sorry, this user has been locked out.",strTestName);

    }

//    @Test
//    public void test(){
//        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS,  " Test1 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS,  " Test1 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS,  " Test1 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS,  " Test1 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.PASS,  " Test1 Passed");
//
//        Assert.assertEquals(1,1);
//        Assert.assertEquals(1,1);
//        Assert.assertEquals(1,1);
//        Assert.assertEquals(1,1);
//    }
//
//    @Test
//    public void test2(){
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test2 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test2 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test2 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test2 Passed");
//
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//    }
//    @Test
//    public void test3(){
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test3 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test3 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test3 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test3 Passed");
//
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//    }
//    @Test
//    public void test4(){
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test4 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test4 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test4 Passed");
//        ExtendTestFactory.getInit().getExtentTest().log(Status.FAIL,  " Test4 Passed");
//
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//        Assert.assertEquals(2,1);
//    }
}
