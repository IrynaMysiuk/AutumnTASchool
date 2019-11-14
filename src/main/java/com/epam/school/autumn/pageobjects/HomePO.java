package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class HomePO {
    private By signInButton = By.xpath("//p[@class='header-auth__signin']//span");
    private By signInModalButton = By.cssSelector(".popup-reg-sign-in-form__sign-in");
    private By emailInput = By.id("signInEmail");
    private By typePassword = By.id("signInPassword");
    private By userName = By.xpath("//div[@class=\"user-info__name\"]/parent::a");
    private By modalTitle = By.xpath("//div[@class=\"popup-title__logo\"]/following-sibling::div[@ng-switch-when=\"signIn\"]");
    private By errorMessage = By.xpath("//div[@ng-show=\"authError\"]");

    public void clickSignIn() {
        getDriver().findElement(signInButton).click();
    }

    public void clickSignInWait() {

        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(signInModalButton));
    }

    public void clickModalSignIn() {
        getDriver().findElement(signInModalButton).click();
    }

    public void typeEmail(String email) {
        getDriver().findElement(emailInput).sendKeys(email);
    }

    public void typePassword(String password) {
        getDriver().findElement(typePassword).sendKeys(password);
    }

    public String getSignInValue() {
        return getDriver().findElement(signInModalButton).getAttribute("value");
    }

    public String getUserName() {
        return getDriver().findElement(userName).getText();
    }

    public boolean isDisplayedModalTitle() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(modalTitle)).isDisplayed();
    }

    public String getErrorMessage() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(errorMessage)).getText();
    }
}
