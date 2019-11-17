package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class FAQPO {
    private By mainFAQItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"FAQ\")]");
    private By mainTitle = By.xpath("//div[@class=\"hero-banner__heading\" and contains(text(),\"FAQ\")]");
    private By popularQuestions = By.xpath("//label[@class=\"accordion-item__question ng-binding\"]");

    public void fAQItem() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(mainFAQItem)).click();
    }

    public String getMainFAQTitle() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(mainTitle)).getText();
    }

    public List<String> getPopularQuestion() {
        return getDriver().findElements(popularQuestions).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

}
