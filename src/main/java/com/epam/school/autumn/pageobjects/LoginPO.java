package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;

public class LoginPO extends AbstractPO {
    private By signInButton = By.xpath("//p[@class='header-auth__signin']//span");
    private By signInModalButton = By.cssSelector(".popup-reg-sign-in-form__sign-in");
    private By emailInput = By.id("signInEmail");
    private By typePassword = By.id("signInPassword");
    private By userName = By.xpath("//div[@class=\"user-info__name\"]/parent::a");
    private By modalTitle = By.xpath("//div[@class=\"popup-title__logo\"]/following-sibling::div[@ng-switch-when=\"signIn\"]");
    private By errorMessage = By.xpath("//div[@ng-show=\"authError\"]");

    public void clickSignIn() {
        getWebElementWithWait(WaitCondition.VISIBILITY, signInButton).click();
    }

    public void clickSignInWait() {

        getWebElementWithWait(WaitCondition.VISIBILITY, signInModalButton);
    }

    public void clickModalSignIn() {
        getWebElementWithWait(WaitCondition.VISIBILITY, signInModalButton).click();
    }

    public void typeEmail(String email) {
        getWebElementWithWait(WaitCondition.VISIBILITY, emailInput).sendKeys(email);
    }

    public void typePassword(String password) {
        getWebElementWithWait(WaitCondition.VISIBILITY, typePassword).sendKeys(password);
    }

    public String getSignInValue() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, signInModalButton).getAttribute("value");
    }

    public String getUserName() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, userName).getText();
    }

    public boolean isDisplayedModalTitle() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, modalTitle).isDisplayed();
    }

    public String getErrorMessage() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, errorMessage).getText();
    }
}
