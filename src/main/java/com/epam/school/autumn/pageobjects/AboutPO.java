package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AboutPO extends AbstractPO {
    private By mainAboutItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"About\")]");
    private By historyText = By.xpath("//h2[@class=\"section-ui__title history-section__title ng-binding\"]");
    private By dates = By.xpath("//section[@class=\"history-section__period\"]/h5");

    public void aboutItem() {
        getWebElementWithWait(WaitCondition.VISIBILITY, mainAboutItem).click();
    }

    public String getAboutPage() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, historyText).getText();
    }

    public List<String> getDates() {
        return getWebElements(dates).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
