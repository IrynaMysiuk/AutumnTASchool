package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;

public class TrainingListPO extends AbstractPO {
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
        getWebElementWithWait(WaitCondition.CLICKABLE, clearSkill).click();
    }

    public void expandTrainingArrow() {
        getWebElementWithWait(WaitCondition.VISIBILITY, trainingArrow).click();
    }

    public void collapseTrainingArrow() {
        getWebElementWithWait(WaitCondition.VISIBILITY, closeSkillList).click();
    }

    public void clickTrainingList() {
        getWebElementWithWait(WaitCondition.VISIBILITY, trainingListItem).click();
    }

    public String getOurSkills() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, ourSkillsItem).getText();
    }

    public List<String> getTrainingLocation() {
        return getWebElements(trainingLocation).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
