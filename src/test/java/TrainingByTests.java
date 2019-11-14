import com.epam.school.autumn.pageobjects.HomePO;
import com.epam.school.autumn.pageobjects.LanguagePO;
import com.epam.school.autumn.pageobjects.TrainingListPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TrainingByTests extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String CORRECT_LOGIN = "iruna.musyk8a@gmail.com";
    public static final String CORRECT_PASSWORD = "16091999";
    public static final String INCORRECT_LOGIN = "aabbccdd@gmail.co";
    public static final String INCORRECT_PASSWORD = "987654321";


    @Test(description = "Verify login with appropriate credentials")
    public void verifyLoginWithAppropriateCredentials() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
    }

    @Test(description = "Verify login with incorrect credentials")
    public void verifyLoginWithIncorrectCredentials() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertTrue(homePO.isDisplayedModalTitle(), "'Sign in' in modal window is not displayed");
        homePO.typeEmail(INCORRECT_LOGIN);
        homePO.typePassword(INCORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getErrorMessage(), "Login failed. Please try again.",
                "Error message is not correct");
    }

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
        TrainingListPO trainingListPO = new TrainingListPO();
        trainingListPO.clearSkill();
        trainingListPO.expandSkillArrow();
        trainingListPO.chooseSkills();
        trainingListPO.typeSkills("Java");
        trainingListPO.chooseSkillItem();
        trainingListPO.collapseSkillArrow();
        trainingListPO.collectSearchSkillResults().forEach(skill -> Assert.assertTrue(skill.contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", skill)));
        trainingListPO.clearSkill();
    }


    @Test(description = "Verify 'News' Page and Materials section")
    public void verifyNewsPageAndMaterialsSections() throws InterruptedException {
        changeLanguage("English");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement signInModalButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".popup-reg-sign-in-form__sign-in")));
        Assert.assertEquals(signInModalButton.getAttribute("value"), "Sign in", "'Sign in' in modal window is not displayed");
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys(CORRECT_LOGIN);
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys(CORRECT_PASSWORD);
        signInModalButton.click();

        WebElement userName = driver.findElement(By.xpath("//div[@class=\"user-info__name\"]/parent::a"));
        Assert.assertEquals(userName.getText(), "Iryna Mysiuk", "User Name is not correct");
        driver.findElement(By.xpath("//ul[@class=\"main-nav__list\"]/li/a[@class=\"topNavItem news click hover\"]")).click();
        WebElement newsTitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class=\"container\"]/div[@class=\"section-ui__title ng-binding\"]")));
        Assert.assertEquals(newsTitle.getText(), "NEWS", "News title is incorrect");
        List<WebElement> newsArticles = driver.findElements(By.xpath("//div[@ng-repeat=\"category in categories\"]/span[@class=\"ng-binding\"]"));
        newsArticles.forEach(webElement -> Assert.assertTrue(webElement.isDisplayed(), String.format("Article %s is not displayed", webElement.getText())));
        Thread.sleep(10000);

        WebElement materialsItem = driver.findElement(By.xpath("//span[@class=\"ng-binding\" and contains(text(), \"Materials\")]"));
        Assert.assertEquals(materialsItem.getText(), "MATERIALS", "Materials is not correct");
        driver.findElement(By.xpath("//div[@class=\"news-page-item__title\"]"));
        List<WebElement> materialsOrUsefulItem = driver.findElements(By.xpath(" //div[@class=\"news-page-item__title\"]"));
        materialsOrUsefulItem.forEach(webElement -> Assert.assertFalse(webElement.getText().contains("useful") ||
                webElement.getText().contains("materials"), String.format("Link %s is not contained \"useful\" or \"materials\" words", webElement)));
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void verifyTrainingsSearchWorksProperlyWithSearchingInLocations() throws InterruptedException {
        changeLanguage("English");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement signInModalButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".popup-reg-sign-in-form__sign-in")));
        Assert.assertEquals(signInModalButton.getAttribute("value"), "Sign in",
                "'Sign in' in modal window is not displayed");
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys(CORRECT_LOGIN);
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys(CORRECT_PASSWORD);
        signInModalButton.click();

        WebElement userName = driver.findElement(By.xpath("//div[@class=\"user-info__name\"]/parent::a"));
        Assert.assertEquals(userName.getText(), "Iryna Mysiuk", "User Name is not correct");

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//ul[@class=\"main-nav__list\"]/li/a[contains(text(),\"Training list\")]"))).click();
        WebElement trainingListTitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class=\"section-ui__title ng-binding\" and contains(text(),\"OUR SKILLS\")]")));
        Assert.assertEquals(trainingListTitle.getText(), "OUR SKILLS", "Training list is incorrect");

        driver.findElement(By.xpath("//div[@ng-click=\"openChoose()\"]")).click();
        WebElement countries = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("location__countries")));
        Assert.assertTrue(countries.isDisplayed(), "Countries is not displayed");


        driver.findElement(By.xpath("//div[@ng-click=\"activeCountryChoose(locations)\" and contains(text(),\"Ukraine\")]")).click();
        wait.until(ExpectedConditions
                .elementToBeSelected(By.xpath("//li[@class=\"cities ng-scope\"]/label[contains(.,'Lviv')]/input")));
        driver.findElement(By.xpath("//div[@ng-click=\"openChoose()\"]")).click();

        List<WebElement> searchForTraining = driver.findElements(By.xpath("//div[@class=\"training-list__container training-list__desktop\"]//div[@class=\"training-item__location ng-binding\"]"));
        searchForTraining.forEach(webElement -> Assert.assertEquals(webElement.getText(), "Lviv, Ukraine",
                String.format("Search for training with countries %s is not displayed", webElement.getText())));
    }

    public void changeLanguage(String language) {
        driver.findElement(By.className("location-selector__globe")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),\"%s\")]", language)))).click();
        wait.until(ExpectedConditions
                .titleContains("Home"));
    }

//    @AfterMethod
//    public void quitBrowser() {
//        driver.quit();
//    }
}
