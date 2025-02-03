package com.pragmatic.selenium.examples.windows;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.time.Duration;

public class WindowsTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close all windows
        }
    }

    @Test
    public void windowsExampleCode() {
        // Navigate to Url
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");

        // Fetch handle of current window
        String currHandle = driver.getWindowHandle();
        assertNotNull(currHandle);

        // Click on link to open a new window
        driver.findElement(By.linkText("Open new window")).click();

        // Fetch handles of all windows, there will be two, [0]- default, [1] - new window
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        // Assert title of new window
        String title = driver.getTitle();
        assertEquals(title, "Simple Page");

        // Close current window
        driver.close();

        // Switch back to the old tab or window
        driver.switchTo().window((String) windowHandles[0]);

        // Open a new tab and switch to it
        driver.switchTo().newWindow(WindowType.TAB);
        assertEquals(driver.getTitle(), "");

        // Open a new window and switch to it
        driver.switchTo().newWindow(WindowType.WINDOW);
        assertEquals(driver.getTitle(), "");
    }


}
