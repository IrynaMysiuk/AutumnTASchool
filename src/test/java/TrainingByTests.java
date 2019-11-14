import com.epam.school.autumn.business.*;
import com.epam.school.autumn.pageobjects.MenuPO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrainingByTests extends BaseTest {

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

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        TrainingListBO trainingListBO = new TrainingListBO()
                .openTrainingList();
        SkillBO skillBO = new SkillBO()
                .chooseJavaSkill();
        trainingListBO.closeTrainingList();
        skillBO.checkFiteredSkills();
    }


    @Test(description = "Verify 'News' Page and Materials section")
    public void verifyNewsPageAndMaterialsSections() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
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

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void verifyTrainingsSearchWorksProperlyWithSearchingInLocations() {
        new LanguageBO()
                .changeLanguage();
        new LoginBO()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        TrainingListBO trainingListBO = new TrainingListBO()
                .checkSelectedTrainingListItem()
                .openTrainingList();
        new LocationBO()
                .checkCountryList()
                .chooseLocation();
        trainingListBO
                .closeTrainingList()
                .checkLocation();
    }
}
