package com.epam.school.autumn.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";
    public static final String WEBDRIVER_FIREFOX_DRIVER = "webdriver.firefox.marionette";
    public static final String FIREFOX_DRIVER_PATH = "src/main/resources/geckodriver.exe";
    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverManager() {
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void setBrowser(DriverType browser) {
        if (driver == null) {
            switch (browser) {
                case FIREFOX: {
                    System.setProperty(WEBDRIVER_FIREFOX_DRIVER, FIREFOX_DRIVER_PATH);
                    driver = new FirefoxDriver();
                    break;
                }
                default: {
                    System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROME_DRIVER_PATH);
                    driver = new ChromeDriver();
                    break;
                }
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(getDriver(), 20);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public enum DriverType {
        CHROME,
        FIREFOX,
        IE
    }

    public static void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

}