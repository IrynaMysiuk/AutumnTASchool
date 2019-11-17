package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class LocationPO {
    private By countryList = By.className("location__countries");
    private By ukraineItem = By.xpath("//div[@ng-click=\"activeCountryChoose(locations)\" and contains(text(),\"Ukraine\")]");
    private By lvivItem = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Lviv')]/span");

    public boolean isDisplayedCountries() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(countryList)).isDisplayed();
    }

    public void chooseCountry() { getDriver().findElement(ukraineItem).click(); }

    public void chooseCity() { getWait().until(ExpectedConditions.elementToBeClickable(lvivItem)).click(); }


}
