package com.pragmatic.selenium.examples.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByPartialText extends By {
    private final String partialText;

    public ByPartialText(String partialText) {
        this.partialText = partialText;
    }


    @Override
    public List<WebElement> findElements(SearchContext webDriver) {
        String xpath = String.format(
                "//*[contains(text(),'%s')" +
                        " and (self::a or self::button or self::input or self::textarea or self::select or " +
                        "(self::div and @onclick) or (self::span and @onclick))]",
                partialText
        );
        return webDriver.findElements(By.xpath(xpath));
    }
}
