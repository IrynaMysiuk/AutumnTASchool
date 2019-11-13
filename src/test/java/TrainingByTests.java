import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrainingByTests {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String CORRECT_LOGIN = "iruna.musyk8a@gmail.com";
    public static final String CORRECT_PASSWORD = "16091999";
    public static final String INCORRECT_LOGIN = "aabbccdd@gmail.co";
    public static final String INCORRECT_PASSWORD = "987654321";

    @BeforeMethod
    public void prepareEnviroment() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe"); //DOWNLOAD DRIVER !
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://training.by"), "Website has incorrect url");
    }

    @Test(description = "Verify login with appropriate credentials")
    public void verifyLoginWithAppropriateCredentials() {
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
    }

    @Test(description = "Verify login with incorrect credentials")
    public void verifyLoginWithIncorrectCredentials() throws InterruptedException {
        changeLanguage("English");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();

        WebElement modalTitle = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.
                        xpath("//div[@class=\"popup-title__logo\"]/following-sibling::div[@ng-switch-when=\"signIn\"]")));
        Assert.assertTrue(modalTitle.isDisplayed(), "'Sign in' in modal window is not displayed");
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys(INCORRECT_LOGIN);
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys(INCORRECT_PASSWORD);
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        WebElement errorMessage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@ng-show=\"authError\"]")));
        Assert.assertEquals(errorMessage.getText(), "Login failed. Please try again.", "Error message is not correct");
    }

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {


        WebElement signInButton = driver.findElement(By.xpath("//p[@class='header-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys(CORRECT_LOGIN);
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys(CORRECT_PASSWORD);
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement bySkillsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class=\"navigation-item ng-binding\"]")));
        bySkillsButton.click();

        WebElement skillsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Java");

        WebElement javaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Java')]//span")));
        javaCheckbox.click();

        WebElement collapseSkillsArrow = driver
                .findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        skillsSearchResultsList.forEach(element -> Assert.assertTrue(element.getText().contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", element)));


        //ADD OTHER STEPS
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

        WebElement materialsItem=driver.findElement(By.xpath("//span[@class=\"ng-binding\" and contains(text(), \"Materials\")]"));
        Assert.assertEquals(materialsItem.getText(), "MATERIALS", "Materials is not correct");
        driver.findElement(By.xpath("//div[@class=\"news-page-item__title\"]"));
        List<WebElement> materialsOrUsefulItem = driver.findElements(By.xpath(" //div[@class=\"news-page-item__title\"]"));
        materialsOrUsefulItem.forEach(webElement -> Assert.assertFalse( webElement.getText().contains("useful") ||
                webElement.getText().contains("materials"),String.format("Link %s is not contained \"useful\" or \"materials\" words", webElement)));
    }

    public void changeLanguage(String language) {
        driver.findElement(By.className("location-selector__globe")).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(),\"%s\")]", language)))).click();
        wait.until(ExpectedConditions
                .titleContains("Home"));
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}
