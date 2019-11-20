package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class MenuPO extends AbstractPO {
    @FindBy(xpath = "//ul[@class=\"main-nav__list\"]/li/a[@class=\"topNavItem news click hover\"]")
    private WebElement menuItem;
    @FindBy(xpath = "//div[@class=\"container\"]/div[@class=\"section-ui__title ng-binding\"]")
    private WebElement newsTitle;
    @FindBy(xpath = "//div[@ng-repeat=\"category in categories\"]/span[@class=\"ng-binding\"]")
    private List<WebElement> newsArticles;
    @FindBy(xpath = "//span[@class=\"ng-binding\" and contains(text(), \"Materials\")]")
    private WebElement materialsItem;
    @FindBy(xpath = " //div[@class=\"news-page-item__title\"]")
    private List<WebElement> materialsTitles;

    public void clickMenu() {
        getWebElementWithWait(WaitCondition.VISIBILITY, menuItem).click();
    }

    public String getNewsTitle() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, newsTitle).getText();
    }

    public List<Boolean> isDisplayedNewsArticles() {
        return newsArticles.stream()
                .map(WebElement::isDisplayed).collect(Collectors.toList());
    }

    public String getMaterialsItem() {
        getWait().withTimeout(Duration.ofSeconds(200)).pollingEvery(Duration.ofSeconds(25));
        return getWebElementWithWait(WaitCondition.VISIBILITY, materialsItem).getText();
    }

    public List<String> getMaterialsTitles() {
        return materialsTitles.stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
