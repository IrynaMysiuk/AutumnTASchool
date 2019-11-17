package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.AboutPO;
import org.testng.Assert;

import java.util.Arrays;

public class AboutBO {
    private AboutPO aboutPO = new AboutPO();

    public AboutBO checkSelectedAboutItem() {
        aboutPO.aboutItem();
        Assert.assertEquals(aboutPO.getAboutPage(), "HISTORY", "About Page is incorrect");
        return this;
    }

    public AboutBO checkImportantDates() {
        Assert.assertEquals(aboutPO.getDates(), Arrays.asList("2002", "2004", "2007", "2008", "2010", "2014", "2016"), "Incorrect important dates");
        return this;
    }
}
