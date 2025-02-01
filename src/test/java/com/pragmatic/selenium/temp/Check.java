package com.pragmatic.selenium.temp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public class Check  implements ICheck, WrapsElement {

    private final WebElement eleCheck;

    Check(WebElement eleCheck){
        this.eleCheck = eleCheck;
    }


    @Override
    public void check() {
        if (!eleCheck.isSelected()){
            eleCheck.click();
        }
    }

    @Override
    public void uncheck() {
        if (eleCheck.isSelected()){
            eleCheck.click();
        }

    }

    @Override
    public void toggle() {
        eleCheck.click();
    }

    @Override
    public boolean isChecked() {
        return eleCheck.isSelected();
    }

    @Override
    public WebElement getWrappedElement() {
        return eleCheck;
    }
}
