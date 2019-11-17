package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.LanguagePO;

public class LanguageBO {
    private LanguagePO languagePO = new LanguagePO();

    public LoginBO changeLanguage() {
        languagePO.clickOnLanguage();
        languagePO.chooseEnglish();
        return new LoginBO();
    }
}
