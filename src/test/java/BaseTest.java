import com.epam.school.autumn.singleton.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static com.epam.school.autumn.singleton.DriverManager.*;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        setBrowser(DriverManager.DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        getDriver().get("https://training.by/#/Home");
        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://training.by"), "Website has incorrect url");
    }

    @AfterMethod
    public void afterMethod() {
       quitDriver();
    }
}
