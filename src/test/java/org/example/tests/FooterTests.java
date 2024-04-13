package org.example.tests;

import com.github.javafaker.Faker;
import org.example.framework.pages.HomePage;
import org.example.framework.pages.footer.ContactUsPage;
import org.example.framework.pages.footer.FooterGate;
import org.testng.annotations.Test;

import static org.example.framework.utils.CustomAssertionsUtils.assertThat;

public class FooterTests extends BaseTest{


    @Test
    public void TCConnectUs()
    {
        Faker faker=new Faker();
      String actualMessage=  getFooterGate().navigateToFooterSection("Contact Us", ContactUsPage.class)
                .performContactUs(faker.name().fullName(),faker.internet().emailAddress(),faker.phoneNumber().cellPhone(),faker.lorem().sentence(), ContactUsPage.class)
                .getErrorMsg();
        assertThat(actualMessage).isEqualTo("Unable to submit your request. Please, try again later".toLowerCase());
    }


    private FooterGate getFooterGate(){
        return new HomePage()
                .getFooterGate();
    }
}
