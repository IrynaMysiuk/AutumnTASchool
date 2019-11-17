package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class AboutPO {
    private By mainAboutItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"About\")]");
    private By historyText = By.xpath("//h2[@class=\"section-ui__title history-section__title ng-binding\"]");
    private By dates = By.xpath("//section[@class=\"history-section__period\"]/h5");

    public void aboutItem() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(mainAboutItem)).click();
    }

    public String getAboutPage() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(historyText)).getText();
    }

    public List<String> getDates() {
        return getDriver().findElements(dates).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
