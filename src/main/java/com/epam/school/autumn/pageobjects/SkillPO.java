package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;
import static com.epam.school.autumn.singleton.DriverManager.getWait;

public class SkillPO {
    private By javaCheckBox = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Java')]//span");
    private By dataCheckBox = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Data')]//span");
    private By skillItems = By.xpath("//div[@class='training-list__container training-list__desktop']//a");
    private By chooseSkills = By.xpath("//div[@ng-click=\"changeTab('Skill')\"]");
    private By skillInput = By.xpath("//input[@name='training-filter-input']");
    public void chooseDataCheckBoxes() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(dataCheckBox)).click();
    }
    public void chooseJavaCheckBoxes() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(javaCheckBox)).click();
    }
    public List<String> collectSearchSkillResults() {
        return getDriver().findElements(skillItems).stream().map(WebElement::getText).collect(Collectors.toList());
    }
    public void chooseSkills() {
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(chooseSkills)).click();
    }

    public void typeSkills(String skill) {
        getDriver().findElement(skillInput).sendKeys(skill);
    }

}
