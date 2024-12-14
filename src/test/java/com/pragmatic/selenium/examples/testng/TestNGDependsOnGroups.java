package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.Test;

public class TestNGDependsOnGroups {


    @Test (groups = {"group1"})
    public void testMethodGroup1() {
        System.out.println("TestNGDependsOnGroups.testMethodGroup1");
    }


    @Test (groups = {"group2"})
    public void testMethodGroup2() {
        System.out.println("TestNGDependsOnGroups.testMethodGroup2");
    }

    @Test (dependsOnGroups = {"group1", "group2"})
    public void testMethodGroup3() {
        System.out.println("TestNGDependsOnGroups.testMethodGroup3");
    }




}
