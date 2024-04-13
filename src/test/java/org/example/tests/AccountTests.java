package org.example.tests;

import com.github.javafaker.Faker;
import org.example.framework.pages.*;
import org.example.framework.pages.account.*;
import org.testng.annotations.Test;

import static org.example.framework.utils.CustomAssertionsUtils.assertThat;

public class AccountTests extends BaseTest {

    @Test
    public void TCRegister() {
        Faker faker = new Faker();
        String password = faker.internet().password();
        String actualMessage = getAccountGate()
                .navigateToAccountDropdown("register", RegisterPage.class)
                .performRegistration(faker.name().firstName(), faker.name().username(), faker.name().lastName(), faker.internet().emailAddress(), password, password, RegisterPage.class)
                .getOperationResponseMsg("valid");
        assertThat(actualMessage).isEqualTo("thank you for registering with tealium ecommerce.");

    }

    @Test
    public void TCLogin() {
        String actualMessage = getAccountGate()
                .navigateToAccountDropdown("log In", LoginPage.class)
                .performLogin("evytester@example.com", "password123", LoginPage.class)
                .getOperationResponseMsg("valid");
        assertThat(actualMessage).isEqualTo("welcome");
    }

    @Test
    public void TCAccountLinks() {
        String currentURL = getAccountGate()
                .navigateToAccountDropdown("Register", RegisterPage.class).getTitleUsingJavaScript(RegisterPage.class);
        assertThat(currentURL).isEqualTo("Create New Customer Account");

    }

    @Test
    public void TCAddNewAddressBook() {
        Faker faker = new Faker();
        String actualMessage = getAccountGate()
                .navigateToAccountDropdown("Log In", LoginPage.class)
                .performLogin("evytester@example.com", "password123", DashboardPage.class)
                .setDashboard("Address Book", AddressBook.class)
                .addNewAddressBook(
                        faker.name().firstName(),
                        faker.name().name(),
                        faker.name().lastName(),
                        faker.company().name(),
                        faker.phoneNumber().cellPhone(),
                        faker.internet().domainSuffix(),
                        faker.address().fullAddress(),
                        faker.address().secondaryAddress(),
                        faker.address().city(),
                        faker.address().zipCode(),
                        "Israel",
                        AddressBook.class
                ).getAddNewAddressBookSuccessMsg();

        assertThat(actualMessage).isEqualTo("the address has been saved.");
    }

    private AccountGate getAccountGate() {
        return HomePage
                .create().getAccountGate();
    }
}
