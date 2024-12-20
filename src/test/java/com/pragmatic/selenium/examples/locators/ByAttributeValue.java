package com.pragmatic.selenium.examples.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByAttributeValue extends By {
    private final String attributeName  ;
    private final String attributeValue;

    public ByAttributeValue(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public List<WebElement> findElements(SearchContext webDriver) {
        return webDriver.findElements(By.cssSelector(String.format("[%s='%s']", attributeName, attributeValue)));
    }
}
