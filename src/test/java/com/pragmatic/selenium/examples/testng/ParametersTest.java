package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTest {

    @Parameters({"browser"})
    @Test
    public void testMethod5(String browserName) {
        System.out.println("browserName = " + browserName);
        System.out.println("TestPriority.testMethod5");
    }

    @Test
    public void testMethod1() {
        System.out.println("TestPriority.testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("TestPriority.testMethod2");
    }

    @Test
    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }

    @Parameters({"browser"})
    @Test
    public void testMethod4(String browserName) {
        System.out.println("TestPriority.testMethod4");
        System.out.println("browserName = " + browserName);
    }

    @Parameters("db")
    @Test
    public void testNonExistentParameter(@Optional("mysql") String db) {
        System.out.println("ParametersTest.testNonExistentParameter");
        System.out.println("db = " + db);
    }


}
