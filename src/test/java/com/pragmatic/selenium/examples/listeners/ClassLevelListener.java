package com.pragmatic.selenium.examples.listeners;

import org.testng.IClassListener;
import org.testng.ITestClass;

public class ClassLevelListener implements IClassListener {

    @Override
    public void onBeforeClass(ITestClass testClass) {
        System.err.println("Commencing execution for the test class : " + testClass.getRealClass().getName());
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        System.err.println("Completed execution for the test class : " + testClass.getRealClass().getName());
    }
}
