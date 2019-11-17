import com.epam.school.autumn.business.AboutBO;
import com.epam.school.autumn.business.FAQBO;
import com.epam.school.autumn.business.LanguageBO;
import com.epam.school.autumn.business.LoginBO;
import org.testng.annotations.Test;

public class FAQTests extends BaseTest{
    @Test(description = "Check popular question in 'FAQ'item")

    public void checkFAQ() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        new FAQBO()
                .checkSelectedFAQItem()
                .checkPopularQuestion();
    }
}
