import com.epam.school.autumn.singleton.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.epam.school.autumn.singleton.DriverManager.*;
import static com.epam.school.autumn.utils.Constants.URL;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        setBrowser(DriverManager.DriverType.CHROME);
        getDriver().get(URL);
        Assert.assertTrue(getDriver().getCurrentUrl().contains("https://training.by"), "Website has incorrect url");
    }

    @AfterMethod
    public void afterMethod() {
        quitDriver();
    }
}
