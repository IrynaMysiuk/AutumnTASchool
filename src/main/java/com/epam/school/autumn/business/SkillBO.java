package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.SkillPO;
import org.testng.Assert;

public class SkillBO {
    private SkillPO skillPO = new SkillPO();

    public SkillBO chooseJavaSkill() {
        skillPO.chooseSkills();
        skillPO.typeSkills("Java");
        skillPO.chooseJavaCheckBoxes();
        return this;
    }

    public SkillBO checkFilteredSkills(String expectedSkill) {
        skillPO.collectSearchSkillResults().forEach(skill -> Assert.assertTrue(skill.contains(expectedSkill.toUpperCase()),
                String.format("Element %s does not contain '%s' word.", skill, expectedSkill.toUpperCase())));
        return this;
    }

    public SkillBO chooseDataSkill() {
        skillPO.chooseSkills();
        skillPO.typeSkills("Data");
        skillPO.chooseDataCheckBoxes();
        return this;
    }

    public SkillBO choosePascalSkill() {
        skillPO.chooseSkills();
        skillPO.typeSkills("Pascal");
        skillPO.getPascalCheckBoxes();
        Assert.assertTrue(skillPO.getPascalCheckBoxes(), "Pascal Item is empty!");
        return this;
    }
}
