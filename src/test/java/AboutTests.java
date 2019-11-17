import com.epam.school.autumn.business.AboutBO;
import com.epam.school.autumn.business.LanguageBO;
import com.epam.school.autumn.business.LoginBO;
import org.testng.annotations.Test;

public class AboutTests extends BaseTest {

    @Test(description = "Verify dates in 'About'item")

    public void checkAbout() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        new AboutBO()
                .checkSelectedAboutItem()
                .checkImportantDates();

    }
}
