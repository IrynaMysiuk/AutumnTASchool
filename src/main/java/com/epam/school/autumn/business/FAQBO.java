package com.epam.school.autumn.business;

import com.epam.school.autumn.pageobjects.FAQPO;
import org.testng.Assert;

public class FAQBO {
    private FAQPO faqPO = new FAQPO();

    public FAQBO checkSelectedFAQItem() {
        faqPO.fAQItem();
        Assert.assertEquals(faqPO.getMainFAQTitle(), "FAQ", "FAQ Page is incorrect");
        return this;
    }
    public FAQBO checkPopularQuestion(){
        Assert.assertTrue(!faqPO.getPopularQuestion().isEmpty(),"Popular question is empty");
        return this;
    }
}
