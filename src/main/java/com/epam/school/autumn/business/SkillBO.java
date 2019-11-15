package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.SkillPO;
import org.testng.Assert;

public class SkillBO {
    private SkillPO skillPO = new SkillPO();

    public SkillBO chooseJavaSkill() {
        skillPO.chooseSkills();
        skillPO.typeSkills("Java");
        skillPO.chooseSkillItem();
        return this;
    }

    public SkillBO checkFiteredSkills() {
        skillPO.collectSearchSkillResults().forEach(skill -> Assert.assertTrue(skill.contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.", skill)));
        return this;
    }

}
