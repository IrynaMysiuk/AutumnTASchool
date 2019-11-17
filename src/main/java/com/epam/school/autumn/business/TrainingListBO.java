package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.TrainingListPO;
import org.testng.Assert;

public class TrainingListBO {
    private TrainingListPO trainingListPO = new TrainingListPO();

    public TrainingListBO openTrainingList() {
        trainingListPO.clearSkill();
        trainingListPO.expandTrainingArrow();
        return this;
    }

    public TrainingListBO closeTrainingList() {
        trainingListPO.collapseTrainingArrow();
        return this;
    }

    public TrainingListBO checkSelectedTrainingListItem() {
        trainingListPO.clickTrainingList();
        Assert.assertEquals(trainingListPO.getOurSkills(), "OUR SKILLS", "Training list is incorrect");
        return this;
    }

    public TrainingListBO checkLocation() {
        trainingListPO.getTrainingLocation().forEach(webElement -> Assert.assertEquals(webElement, "Lviv, Ukraine",
                String.format("Search for training with countries %s is not displayed", webElement)));
        return this;
    }
}
