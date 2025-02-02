package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementHasExpandedFully  implements ExpectedCondition <Boolean> {
    private final By expandingElement;
    private int lastHeight = -1;

    public ElementHasExpandedFully(By expandingElement) {
        this.expandingElement = expandingElement;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        WebElement element = webDriver.findElement(expandingElement);
        int newHeight = element.getSize().height;

        if (newHeight > lastHeight) {
            lastHeight = newHeight;
            return false; // Still expanding
        }
        return true; // Fully expanded
    }
}
