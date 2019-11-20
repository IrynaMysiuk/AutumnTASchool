package com.epam.school.autumn.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SkillPO extends AbstractPO {
    @FindBy(xpath = "//li[@class=\"cities ng-scope\"]/label[contains(.,'Java')]//span")
    private WebElement javaCheckBox;
    @FindBy(xpath = "//li[@class=\"cities ng-scope\"]/label[contains(.,'Data')]//span")
    private WebElement dataCheckBox;
    @FindBy(xpath = "//li[@class=\"cities ng-scope\"]/label[contains(.,'Pascal')]//span")
    private List<WebElement> pascalCheckBox;
    @FindBy(xpath = "//div[@class='training-list__container training-list__desktop']//a")
    private List<WebElement> skillItems;
    @FindBy(xpath = "//div[@ng-click=\"changeTab('Skill')\"]")
    private WebElement chooseSkills;
    @FindBy(xpath = "//input[@name='training-filter-input']")
    private WebElement skillInput;

    public void chooseDataCheckBoxes() {
        getWebElementWithWait(WaitCondition.VISIBILITY, dataCheckBox).click();
    }

    public void chooseJavaCheckBoxes() {
        getWebElementWithWait(WaitCondition.VISIBILITY, javaCheckBox).click();
    }

    public boolean getPascalCheckBoxes() {
        return pascalCheckBox.isEmpty();
    }

    public List<String> collectSearchSkillResults() {
        return skillItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void chooseSkills() {
        getWebElementWithWait(WaitCondition.VISIBILITY, chooseSkills).click();
    }

    public void typeSkills(String skill) {
        getWebElementWithWait(WaitCondition.VISIBILITY, skillInput).sendKeys(skill);
    }

}
