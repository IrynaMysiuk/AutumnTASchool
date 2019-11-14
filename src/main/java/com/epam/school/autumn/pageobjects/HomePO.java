package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class HomePO {
    private By signInButton = By.xpath("//p[@class='header-auth__signin']//span");
    private By signInModalButton = By.cssSelector(".popup-reg-sign-in-form__sign-in");
    private By emailInput = By.id("signInEmail");
    private By typePassword = By.id("signInPassword");

    public void clickSignInWait() {

        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".popup-reg-sign-in-form__sign-in")));
    }

    public void clickModalSignIn() {
        getDriver().findElement(signInModalButton).click();
    }

    public void emailInput() {
        getDriver().findElement(emailInput).click();
    }

    public void typePassword() {
        getDriver().findElement(typePassword).click();
    }

    public String getSignInValue() {
        return getDriver().findElement(signInModalButton).getAttribute("value");
    }
}
