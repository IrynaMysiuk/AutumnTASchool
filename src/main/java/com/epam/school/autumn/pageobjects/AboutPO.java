package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class AboutPO extends AbstractPO {
    @FindBy(xpath = "//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"About\")]")
    private WebElement mainAboutItem;
    @FindBy(xpath = "//h2[@class=\"section-ui__title history-section__title ng-binding\"]")
    private WebElement historyText;
    @FindBy(xpath = "//section[@class=\"history-section__period\"]/h5")
    private List<WebElement> dates;


    public void aboutItem() {
        getWebElementWithWait(WaitCondition.VISIBILITY, mainAboutItem).click();
    }

    public String getAboutPage() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, historyText).getText();
    }

    public List<String> getDates() {
        return dates.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
