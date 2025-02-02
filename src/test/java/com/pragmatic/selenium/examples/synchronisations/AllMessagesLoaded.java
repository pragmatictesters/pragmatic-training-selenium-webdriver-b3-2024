package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AllMessagesLoaded implements ExpectedCondition<Boolean> {

    private final By childElement;
    private int previousElementCount = -1;

    public AllMessagesLoaded(By childElement) {
        this.childElement = childElement;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        // Find the number of elements matching the locator
        int currentElementCount = driver.findElements(childElement).size();

        // If the count has increased, it means new elements are still loading
        if (currentElementCount > previousElementCount) {
            System.out.printf("currentElementCount %d > previousElementCount %d\n", currentElementCount, previousElementCount);
            previousElementCount = currentElementCount;  // Update the previous count
            return false;  // Wait for more elements to load
        }

        // If the count hasn't changed, the loading is complete
        return true;
    }
}
