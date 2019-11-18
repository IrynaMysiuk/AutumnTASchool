package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;

public class MenuPO extends AbstractPO {
    private By menuItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[@class=\"topNavItem news click hover\"]");
    private By newsTitle = By.xpath("//div[@class=\"container\"]/div[@class=\"section-ui__title ng-binding\"]");
    private By newsArticles = By.xpath("//div[@ng-repeat=\"category in categories\"]/span[@class=\"ng-binding\"]");
    private By materialsItem = By.xpath("//span[@class=\"ng-binding\" and contains(text(), \"Materials\")]");
    private By materialsTitles = By.xpath(" //div[@class=\"news-page-item__title\"]");

    public void clickMenu() {
        getWebElementWithWait(WaitCondition.VISIBILITY, menuItem).click();
    }

    public String getNewsTitle() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, newsTitle).getText();
    }

    public List<Boolean> isDisplayedNewsArticles() {
        new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofSeconds(25));
        return getWebElements(newsArticles).stream()
                .map(WebElement::isDisplayed).collect(Collectors.toList());
    }

    public String getMaterialsItem() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, materialsItem).getText();
    }

    public List<String> getMaterialsTitles() {
        return getWebElements(materialsTitles).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
