package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.school.autumn.singleton.DriverManager.getWait;


public class LanguagePO extends AbstractPO {
    @FindBy(className = "location-selector__globe")
    private WebElement languageButton;
    @FindBy(xpath = "//a[contains(text(),\"English\")]")
    private WebElement englishButton;
    private String homeTitle = "Home";


    public void clickOnLanguage() {
        getWebElementWithWait(WaitCondition.VISIBILITY, languageButton).click();
    }


    public void chooseEnglish() {
        getWebElementWithWait(WaitCondition.VISIBILITY, englishButton).click();
        getWait().until(ExpectedConditions.titleContains(homeTitle));
    }

}
