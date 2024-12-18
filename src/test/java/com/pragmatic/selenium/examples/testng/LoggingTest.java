package com.pragmatic.selenium.examples.testng;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class LoggingTest {

    private static final Logger LOGGER = Logger.getLogger(LoggingTest.class);


    @Test
    public void testLogging() {
        LOGGER.debug("entering myMethod()");
    }
}
