package com.UIautomation.utilities;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class DataProviderTesting {
    //I can use microsoft excel(use poi library) for data provider to get data and change it to Object[][]
    //But if you want high performance, better not use it. But it's a choice
    //If use Microsoft Excel, we can maintain easily
    //There is always a cost for every choose.

    @DataProvider(name = "ParamForTest1",parallel = true)
    public Object[][] objForTest1(){
        Map<String,Object> mapParamsData1 = new HashMap<>();
        mapParamsData1.put("URL","https://www.saucedemo.com");
        mapParamsData1.put("UserName","standard_user");
        mapParamsData1.put("Password","secret_sauce");
        mapParamsData1.put("ExpectedItem","Sauce Labs Fleece Jacket");
        mapParamsData1.put("ExpectedPrice","$49.99");
        mapParamsData1.put("FirstName","Kevin");
        mapParamsData1.put("LastName","Hart");
        mapParamsData1.put("ZipPostalCode","12345");

        Map<String,Object> mapParamsData2 = new HashMap<>();
        mapParamsData2.put("URL","https://www.saucedemo.com");
        mapParamsData2.put("UserName","standard_user");
        mapParamsData2.put("Password","secret_sauce");
        mapParamsData2.put("ExpectedItem","Sauce Labs Fleece Jacket");
        mapParamsData2.put("ExpectedPrice","$49.99");
        mapParamsData2.put("FirstName","Kevin");
        mapParamsData2.put("LastName","Chen");
        mapParamsData2.put("ZipPostalCode","54321");


        return new Object[][]{
                {mapParamsData1},
                {mapParamsData2}
        };
    }

    @DataProvider(name = "ParamForTest2",parallel = true)
    public Object[][] objForTest2(){
        Map<String,Object> mapParamsData = new HashMap<>();
        mapParamsData.put("URL","https://www.saucedemo.com");
        mapParamsData.put("UserName","locked_out_user");
        mapParamsData.put("Password","secret_sauce");
        mapParamsData.put("ExpectedItem","Sauce Labs Fleece Jacket");
        mapParamsData.put("ExpectedPrice","$49.99");
        mapParamsData.put("FirstName","Te");
        mapParamsData.put("LastName","st");
        mapParamsData.put("ZipPostalCode","123452");


        return new Object[][]{
                {mapParamsData}
        };
    }



}
