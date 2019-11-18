package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class FAQPO extends AbstractPO {
    private By mainFAQItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"FAQ\")]");
    private By mainTitle = By.xpath("//div[@class=\"hero-banner__heading\" and contains(text(),\"FAQ\")]");
    private By popularQuestions = By.xpath("//label[@class=\"accordion-item__question ng-binding\"]");

    public void fAQItem() {
        getWebElementWithWait(WaitCondition.VISIBILITY, mainFAQItem).click();
    }

    public String getMainFAQTitle() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, mainTitle).getText();
    }

    public List<String> getPopularQuestion() {
        return getWebElements(popularQuestions).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

}
