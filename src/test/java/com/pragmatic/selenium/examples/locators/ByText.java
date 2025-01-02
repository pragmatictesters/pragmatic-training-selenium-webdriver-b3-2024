package com.pragmatic.selenium.examples.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByText extends By {
    private final String visibleText;

    public ByText(String visibleText) {
        this.visibleText = visibleText;
    }

    @Override
    public List<WebElement> findElements(SearchContext webDriver) {
        return webDriver.findElements(By.xpath(String.format("//*[text()='%s']", visibleText)));
    }
}
