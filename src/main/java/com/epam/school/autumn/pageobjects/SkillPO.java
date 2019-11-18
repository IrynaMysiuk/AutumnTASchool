package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;

public class SkillPO extends AbstractPO {
    private By javaCheckBox = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Java')]//span");
    private By dataCheckBox = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Data')]//span");
    private By pascalCheckBox = By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Pascal')]//span");
    private By skillItems = By.xpath("//div[@class='training-list__container training-list__desktop']//a");
    private By chooseSkills = By.xpath("//div[@ng-click=\"changeTab('Skill')\"]");
    private By skillInput = By.xpath("//input[@name='training-filter-input']");

    public void chooseDataCheckBoxes() {
        getWebElementWithWait(WaitCondition.VISIBILITY, dataCheckBox).click();
    }

    public void chooseJavaCheckBoxes() {
        getWebElementWithWait(WaitCondition.VISIBILITY, javaCheckBox).click();
    }

    public boolean getPascalCheckBoxes() {
        return getDriver().findElements(pascalCheckBox).isEmpty();

    }

    public List<String> collectSearchSkillResults() {
        return getWebElements(skillItems).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void chooseSkills() {
        getWebElementWithWait(WaitCondition.VISIBILITY, chooseSkills).click();
    }

    public void typeSkills(String skill) {
        getWebElementWithWait(WaitCondition.VISIBILITY, skillInput).sendKeys(skill);
    }

}
