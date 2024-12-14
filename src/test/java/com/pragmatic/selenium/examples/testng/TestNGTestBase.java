package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.*;

public class TestNGTestBase {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("TestNGTestBase.beforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("TestNGTestBase.afterSuite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("TestNGTestBase.beforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("TestNGTestBase.afterTest");
    }


    @BeforeGroups ("smoke")
    public void beforeGroupsSmoke(){
        System.out.println("TestNGTestBase.beforeGroupsSmoke");
    }

    @AfterGroups("smoke")
    public void afterGroupsSmoke(){
        System.out.println("TestNGTestBase.afterGroupsSmoke");
    }

    @BeforeGroups ("regression")
    public void beforeGroupsRegression(){
        System.out.println("TestNGTestBase.beforeGroupsRegression");
    }

    @AfterGroups("regression")
    public void afterGroupsRegression(){
        System.out.println("TestNGTestBase.afterGroupsRegression");
    }

}
