import com.epam.school.autumn.business.LanguageBO;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(description = "Verify login with appropriate credentials")
    public void verifyLoginWithAppropriateCredentials() {
        new LanguageBO()
                .changeLanguage()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
    }

    @Test(description = "Verify login with incorrect credentials")
    public void verifyLoginWithIncorrectCredentials() {
        new LanguageBO()
                .changeLanguage()
                .signInButton()
                .checkModalTitle()
                .signInWithIncorrectData()
                .checkErrorMessage();
    }

}
