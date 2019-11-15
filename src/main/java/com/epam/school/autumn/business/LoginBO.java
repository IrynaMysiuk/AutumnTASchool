package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.LoginPO;
import org.testng.Assert;

import static com.epam.school.autumn.utils.Constants.*;

public class LoginBO {
    private LoginPO loginPO = new LoginPO();

    public LoginBO signInWithCorrectData() {
        loginPO.typeEmail(CORRECT_LOGIN);
        loginPO.typePassword(CORRECT_PASSWORD);
        loginPO.clickModalSignIn();
        return this;
    }

    public LoginBO signInWithIncorrectData() {
        loginPO.typeEmail(INCORRECT_LOGIN);
        loginPO.typePassword(INCORRECT_PASSWORD);
        loginPO.clickModalSignIn();
        return this;
    }

    public LoginBO signInButton() {
        loginPO.clickSignIn();
        return this;
    }

    public LoginBO checkSignInValue() {
        Assert.assertEquals(loginPO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        return this;
    }

    public LoginBO checkUserName() {
        Assert.assertEquals(loginPO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
        return this;
    }

    public LoginBO checkModalTitle() {
        Assert.assertTrue(loginPO.isDisplayedModalTitle(), "'Sign in' in modal window is not displayed");
        return this;
    }

    public LoginBO checkErrorMessage() {
        Assert.assertEquals(loginPO.getErrorMessage(), "Login failed. Please try again.",
                "Error message is not correct");
        return this;
    }
}
