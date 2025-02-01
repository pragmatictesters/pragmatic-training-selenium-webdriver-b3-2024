package com.pragmatic.selenium.examples.synchronisations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ElementHasExpandedFully  implements ExpectedCondition <Boolean> {
    private final By expandingElement;
    private int lastHeight;

    public ElementHasExpandedFully(By expandingElement) {
        this.expandingElement = expandingElement;
    }


    @Override
    public Boolean apply(WebDriver webDriver) {
        int newHeight = webDriver.findElement(expandingElement).getSize().height;
        if (newHeight> lastHeight) {
            lastHeight = newHeight;
            return false;
        } else {
            return true;
        }
    }
}
