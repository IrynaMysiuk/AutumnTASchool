package com.epam.school.autumn.pageobjects;

import com.epam.school.autumn.singleton.DriverManager;
import org.openqa.selenium.By;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;

public class HomePO {
    private By signInButton = By.xpath("//p[@class='header-auth__signin']//span");
    private By signInModalButton = By.cssSelector(".popup-reg-sign-in-form__sign-in");

    public void clickSignIn() {
        getDriver().findElement(signInButton).click();
    }
    public void clickModalSignIn() {
        getDriver().findElement(signInModalButton).click();
    }

}
