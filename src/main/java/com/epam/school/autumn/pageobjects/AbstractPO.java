package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class AbstractPO {
    protected WebElement getWebElement(By locator) {
        return getDriver().findElement(locator);
    }

    protected List<WebElement> getWebElements(By locator) {
        return getDriver().findElements(locator);
    }

    protected WebElement getWebElementWithWait(WaitCondition waitCondition, By locator) {
        if (waitCondition.equals(WaitCondition.VISIBILITY))
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        else if (waitCondition.equals(WaitCondition.CLICKABLE))
            return getWait().until(ExpectedConditions.elementToBeClickable(locator));
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public enum WaitCondition {
        VISIBILITY,
        CLICKABLE
    }
}