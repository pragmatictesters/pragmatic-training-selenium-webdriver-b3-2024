package com.pragmatic.selenium.examples.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class Check  implements WrapsElement , ICheck {
    private final WebElement checkbox;

    public Check(WebElement checkbox) {
        this.checkbox = checkbox;
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

    }
}
