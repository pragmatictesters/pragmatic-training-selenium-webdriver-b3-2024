package com.pragmatic.selenium.examples.listeners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({ClassLevelListener.class})
public class SampleTestCase {


    @BeforeMethod
    public void setup() {
        System.out.println("I am in before method");
    }

    @Test
    public void testMethod1() {
        System.out.println("I am in test method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("I am in test method 2 ");
    }
}
