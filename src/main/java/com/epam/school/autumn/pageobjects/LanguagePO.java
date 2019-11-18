package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.school.autumn.singleton.DriverManager.getWait;


public class LanguagePO extends AbstractPO {
    private By languageButton = By.className("location-selector__globe");
    private By englishButton = By.xpath("//a[contains(text(),\"English\")]");
    private String homeTitle = "Home";


    public void clickOnLanguage() {
        getWebElementWithWait(WaitCondition.VISIBILITY, languageButton).click();
    }


    public void chooseEnglish() {
        getWebElementWithWait(WaitCondition.VISIBILITY, englishButton).click();
        getWait().until(ExpectedConditions.titleContains(homeTitle));
    }

}
