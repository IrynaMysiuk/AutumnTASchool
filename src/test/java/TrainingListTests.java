import com.epam.school.autumn.business.LanguageBO;
import com.epam.school.autumn.business.LocationBO;
import com.epam.school.autumn.business.SkillBO;
import com.epam.school.autumn.business.TrainingListBO;
import org.testng.annotations.Test;

public class TrainingListTests extends BaseTest {
    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        new LanguageBO()
                .changeLanguage()
                .signInButton()
                .checkSignInValue()
                .signInWithCorrectData()
                .checkUserName();
        TrainingListBO trainingListBO = new TrainingListBO()
                .openTrainingList();
        SkillBO skillBO = new SkillBO()
                .chooseJavaSkill();
        trainingListBO.closeTrainingList();
        skillBO.checkFilteredSkills("Java");
        trainingListBO.openTrainingList();
        skillBO.chooseDataSkill()
                .checkFilteredSkills("Data")
                .choosePascalSkill();
    }

    @Test(description = "Verify ‘Trainings’ search works properly with searching in ‘Locations’.")
    public void verifyTrainingsSearchWorksProperlyWithSearchingInLocations() {
        new LanguageBO()
                .changeLanguage()
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
