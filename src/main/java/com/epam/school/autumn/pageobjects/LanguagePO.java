package com.epam.school.autumn.pageobjects;

import com.epam.school.autumn.singleton.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;


public class LanguagePO {
    private By languageButton = By.className("location-selector__globe");
    private By englishButton = By.xpath("//a[contains(text(),\"English\")]");
    private String homeTitle = "Home";


    public void clickOnLanguage() {
        getDriver().findElement(languageButton).click();
    }


    public void chooseEnglish() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(englishButton)).click();
        getWait().until(ExpectedConditions
                .titleContains(homeTitle));
    }

}
