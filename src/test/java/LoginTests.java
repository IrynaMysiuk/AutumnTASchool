import com.epam.school.autumn.business.LanguageBO;
import com.epam.school.autumn.business.LoginBO;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(description = "Verify login with appropriate credentials")
    public void verifyLoginWithAppropriateCredentials() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
    }

    @Test(description = "Verify login with incorrect credentials")
    public void verifyLoginWithIncorrectCredentials() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkModalTitle()
                .signInWithIncorrectData()
                .checkErrorMessage();
    }

}
