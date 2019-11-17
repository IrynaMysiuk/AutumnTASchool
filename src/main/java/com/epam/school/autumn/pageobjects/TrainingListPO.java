package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class TrainingListPO {
    private By clearSkill = By
            .cssSelector("span.filter-field__input-item-close-icon.filter-field__input-item-close-icon--common");
    private By trainingArrow = By.className("filter-toggle__arrow-icon");
    private By closeSkillList = By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']");
    private By trainingListItem = By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"Training list\")]");
    private By ourSkillsItem = By.xpath("//div[@class=\"section-ui__title ng-binding\" and contains(text(),\"OUR SKILLS\")]");
    private By trainingLocation = By
            .xpath("//div[@class=\"training-list__container training-list__desktop\"]//div[@class=\"training-item__location ng-binding\"]");

    public void clearSkill() {
        new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(1000)).pollingEvery(Duration.ofSeconds(25));
        getWait().until(ExpectedConditions
                .elementToBeClickable(clearSkill)).click();
    }

    public void expandTrainingArrow() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(trainingArrow)).click();
    }

    public void collapseTrainingArrow() {
        getDriver().findElement(closeSkillList).click();
    }

    public void clickTrainingList() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(trainingListItem)).click();
    }

    public String getOurSkills() {
        return getWait().until(ExpectedConditions
                .visibilityOfElementLocated(ourSkillsItem)).getText();
    }

    public List<String> getTrainingLocation() {
        return getDriver().findElements(trainingLocation).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
