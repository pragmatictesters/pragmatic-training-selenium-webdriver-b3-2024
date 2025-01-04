package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.Test;

@Test
public class ClassLevelAnnotationTest {

    public void testMethod5() {
        System.out.println("TestPriority.testMethod5");
    }


    public void testMethod1() {
        System.out.println("TestPriority.testMethod1");
    }

    @Test(invocationCount = 3)
    public void testMethod2() {
        System.out.println("TestPriority.testMethod2");
    }


    public void testMethod3() {
        System.out.println("TestPriority.testMethod3");
    }


    public void testMethod4() {
        System.out.println("TestPriority.testMethod4");
    }

    private String getString(){
        return "Hello";
    }

}
