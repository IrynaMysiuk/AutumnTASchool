package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;

public class LocationPO extends AbstractPO {
    private By countryList = By.className("location__countries");
    private By ukraineItem = By.xpath("//div[@ng-click=\"activeCountryChoose(locations)\" and contains(text(),\"Ukraine\")]");
    private By lvivItem = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Lviv')]/span");

    public boolean isDisplayedCountries() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, countryList).isDisplayed();
    }

    public void chooseCountry() {
        getWebElementWithWait(WaitCondition.VISIBILITY, ukraineItem).click();
    }

    public void chooseCity() {
        getWebElementWithWait(WaitCondition.CLICKABLE, lvivItem).click();
    }


}
