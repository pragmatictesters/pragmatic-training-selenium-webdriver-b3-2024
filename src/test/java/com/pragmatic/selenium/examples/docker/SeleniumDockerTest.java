package com.pragmatic.selenium.examples.docker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumDockerTest {
    public static void main(String[] args) throws MalformedURLException {
        // Define Grid URL
        String gridURL = "http://localhost:4444/wd/hub";

        // Use ChromeOptions instead of DesiredCapabilities
        FirefoxOptions options = new FirefoxOptions();

        // Initialize WebDriver
        WebDriver driver = new RemoteWebDriver(new URL(gridURL), options);

        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }
}
