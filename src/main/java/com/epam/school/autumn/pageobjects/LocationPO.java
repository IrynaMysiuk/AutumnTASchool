package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocationPO extends AbstractPO {
    @FindBy(className = "location__countries")
    private WebElement countryList;
    @FindBy(xpath = "//div[@ng-click=\"activeCountryChoose(locations)\" and contains(text(),\"Ukraine\")]")
    private WebElement ukraineItem;
    @FindBy(xpath = "//li[@class=\"cities ng-scope\"]/label[contains(.,'Lviv')]/span")
    private WebElement lvivItem;

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
