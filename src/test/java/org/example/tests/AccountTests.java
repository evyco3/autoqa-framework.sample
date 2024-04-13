package org.example.tests;

import com.github.javafaker.Faker;
import org.example.framework.pages.AccountGate;
import org.example.framework.pages.HomePage;
import org.example.framework.pages.LoginPage;
import org.example.framework.pages.RegisterPage;
import org.example.framework.utils.CustomAssertionsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.framework.utils.CustomAssertionsUtils.assertThat;

public class AccountTests extends BaseTest {

    @Test
    public void TCRegister() {
        Faker faker = new Faker();
        String password = faker.internet().password();
        String actualMessage=getAccountGate()
                .set("Register", RegisterPage.class)
                .performRegistration(faker.name().firstName(), faker.name().username(), faker.name().lastName(), faker.internet().emailAddress(), password, password, RegisterPage.class)
                .getOperationResponseMsg("valid");
         assertThat(actualMessage).isEqualTo("Thank you for registering with Tealium Ecommerce.");

    }

    @Test
    public void TCLogin(){
        String actualMessage=getAccountGate()
                .set("Log In", LoginPage.class)
                .performLogin("evytester@example.com","password123", LoginPage.class)
                .getOperationResponseMsg("valid");
        assertThat(actualMessage).isEqualTo("WELCOME");
    }

    private AccountGate getAccountGate(){
        return HomePage
                .create().getAccountGate();
    }
}
