package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.LocationPO;
import org.testng.Assert;

public class LocationBO {
    private LocationPO locationPO = new LocationPO();

    public LocationBO checkCountryList() {
        Assert.assertTrue(locationPO.isDisplayedCountries(), "Countries is not displayed");
        return this;
    }

    public LocationBO chooseLocation() {
        locationPO.chooseCountry();
        locationPO.chooseCity();
        return this;
    }
}
