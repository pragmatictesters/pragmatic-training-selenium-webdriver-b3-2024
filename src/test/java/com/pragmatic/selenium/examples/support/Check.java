package com.pragmatic.selenium.examples.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class Check  implements WrapsElement , ICheck {
    private final WebElement checkbox;

    public Check(WebElement checkbox) {

        String tagName = checkbox.getTagName();
        String typeAttribute = checkbox.getDomAttribute("type");

        if (tagName != null && "input".equalsIgnoreCase(tagName)
                && typeAttribute != null && "checkbox".equalsIgnoreCase(typeAttribute)) {
            this.checkbox = checkbox;
        } else {
            throw new UnexpectedTagNameException("Expected <input type='checkbox'> but found <" + tagName + " type='"
                    + checkbox.getDomAttribute("type") + "'>", tagName);
        }
    }

    @Override
    public WebElement getWrappedElement() {
        return checkbox;
    }

    @Override
    public boolean isChecked() {
        return checkbox.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return checkbox.isEnabled();
    }

    @Override
    public boolean isVisible() {
        return checkbox.isDisplayed();
    }

    @Override
    public String getLabel() {
        WebElement eleCheck = this.getWrappedElement();
        WebElement label = eleCheck.findElement(By.xpath(".."));
        return label.getText().trim();
    }

    @Override
    public void check() {
        if(!isChecked()){
            checkbox.click();
        }
    }

    @Override
    public void uncheck() {
        if(isChecked()){
            checkbox.click();
        }
    }

    @Override
    public void toggle() {
        checkbox.click();
    }
}
