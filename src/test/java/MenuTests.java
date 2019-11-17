import com.epam.school.autumn.business.LanguageBO;
import com.epam.school.autumn.pageobjects.MenuPO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTests extends BaseTest {
    @Test(description = "Verify 'News' Page and Materials section")
    public void verifyNewsPageAndMaterialsSections() {
        new LanguageBO()
                .changeLanguage()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        MenuPO menuPo = new MenuPO();
        menuPo.clickMenu();
        Assert.assertEquals(menuPo.getNewsTitle(), "NEWS", "News title is incorrect");
        menuPo.isDisplayedNewsArticles().forEach(webElement -> Assert.assertTrue(webElement,
                String.format("Article %s is not displayed", webElement)));
        Assert.assertEquals(menuPo.getMaterialsItem(), "MATERIALS", "Materials is not correct");
        menuPo.getMaterialsTitles().forEach(webElement -> Assert.assertFalse(webElement.contains("useful") ||
                webElement.contains("materials"), String.format("Link %s is not contained \"useful\" or \"materials\" words", webElement)));

    }
}
