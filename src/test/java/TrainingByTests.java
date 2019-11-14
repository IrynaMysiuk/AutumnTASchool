import com.epam.school.autumn.pageobjects.HomePO;
import com.epam.school.autumn.pageobjects.LanguagePO;
import com.epam.school.autumn.pageobjects.MenuPO;
import com.epam.school.autumn.pageobjects.TrainingListPO;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.school.autumn.utils.Constants.*;

public class TrainingByTests extends BaseTest {

    @Test(description = "Verify login with appropriate credentials")
    public void verifyLoginWithAppropriateCredentials() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
    }

    @Test(description = "Verify login with incorrect credentials")
    public void verifyLoginWithIncorrectCredentials() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertTrue(homePO.isDisplayedModalTitle(), "'Sign in' in modal window is not displayed");
        homePO.typeEmail(INCORRECT_LOGIN);
        homePO.typePassword(INCORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getErrorMessage(), "Login failed. Please try again.",
                "Error message is not correct");
    }

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
        TrainingListPO trainingListPO = new TrainingListPO();
        trainingListPO.clearSkill();
        trainingListPO.expandTrainingArrow();
        trainingListPO.chooseSkills();
        trainingListPO.typeSkills("Java");
        trainingListPO.chooseSkillItem();
        trainingListPO.collapseTrainingArrow();
        trainingListPO.collectSearchSkillResults().forEach(skill -> Assert.assertTrue(skill.contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", skill)));
        trainingListPO.clearSkill();
    }


    @Test(description = "Verify 'News' Page and Materials section")
    public void verifyNewsPageAndMaterialsSections() throws InterruptedException {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
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
    public void verifyTrainingsSearchWorksProperlyWithSearchingInLocations() throws InterruptedException {
        LanguagePO languagePO = new LanguagePO();
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        HomePO homePO = new HomePO();
        homePO.clickSignIn();
        Assert.assertEquals(homePO.getSignInValue(), "Sign in", "'Sign in' in modal window is not displayed");
        homePO.typeEmail(CORRECT_LOGIN);
        homePO.typePassword(CORRECT_PASSWORD);
        homePO.clickModalSignIn();
        Assert.assertEquals(homePO.getUserName(), "Iryna Mysiuk", "User Name is not correct");
        TrainingListPO trainingListPO = new TrainingListPO();
        trainingListPO.clickTrainingList();
        Assert.assertEquals(trainingListPO.getOurSkills(), "OUR SKILLS", "Training list is incorrect");
        trainingListPO.expandTrainingArrow();
        Assert.assertTrue(trainingListPO.isDisplayedCountries(), "Countries is not displayed");
        trainingListPO.chooseCountry();
        trainingListPO.chooseCity();
        trainingListPO.collapseTrainingArrow();
        trainingListPO.getTrainingLocation().forEach(webElement -> Assert.assertEquals(webElement, "Lviv, Ukraine",
                String.format("Search for training with countries %s is not displayed", webElement)));
    }
}
