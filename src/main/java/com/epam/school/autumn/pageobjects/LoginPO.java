package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends AbstractPO {
    @FindBy(xpath = "//p[@class='header-auth__signin']//span")
    private WebElement signInButton;
    @FindBy(css = ".popup-reg-sign-in-form__sign-in")
    private WebElement signInModalButton;
    @FindBy(id = "signInEmail")
    private WebElement emailInput;
    @FindBy(id = "signInPassword")
    private WebElement typePassword;
    @FindBy(xpath = "//div[@class=\"user-info__name\"]/parent::a")
    private WebElement userName;
    @FindBy(xpath = "//div[@class=\"popup-title__logo\"]/following-sibling::div[@ng-switch-when=\"signIn\"]")
    private WebElement modalTitle;
    @FindBy(xpath = "//div[@ng-show=\"authError\"]")
    private WebElement errorMessage;

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
