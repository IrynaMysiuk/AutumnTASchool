package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class FAQPO extends AbstractPO {
    @FindBy(xpath = "//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"FAQ\")]")
    private WebElement mainFAQItem;
    @FindBy(xpath = "//div[@class=\"hero-banner__heading\" and contains(text(),\"FAQ\")]")
    private WebElement mainTitle;
    @FindBy(xpath = "//label[@class=\"accordion-item__question ng-binding\"]")
    private List<WebElement> popularQuestions;


    public void fAQItem() {
        getWebElementWithWait(WaitCondition.VISIBILITY, mainFAQItem).click();
    }

    public String getMainFAQTitle() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, mainTitle).getText();
    }

    public List<String> getPopularQuestion() {
        return popularQuestions.stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

}
