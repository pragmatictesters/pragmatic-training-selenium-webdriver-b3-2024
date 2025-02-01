package com.pragmatic.selenium.temp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class Button implements  IButton, WrapsElement {
    private final WebElement eleButton;

    public Button(WebElement eleButton) {
        String tagName = eleButton.getTagName();

        if (null != tagName && "input".equals(tagName.toLowerCase()) && "submit".equals(eleButton.getDomAttribute("type"))) {
            this.eleButton = eleButton;
        } else {
            throw new UnexpectedTagNameException("input", tagName);
        }
    }

    public void click() {
        eleButton.click();
    }

    public boolean isEnabled() {
        return eleButton.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return eleButton.isDisplayed();
    }

    @Override
    public WebElement getWrappedElement() {
        return eleButton;
    }
}
