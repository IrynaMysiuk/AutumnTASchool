package com.epam.school.autumn.pageobjects;

import com.epam.school.autumn.singleton.DriverManager;
import org.openqa.selenium.By;

import static com.epam.school.autumn.singleton.DriverManager.getDriver;

public class LoginPO{
    private By emailInput = By.id("signInEmail\")");

    public void typeEmail(String email){
        getDriver().findElement(emailInput).sendKeys(email);
    }

}
