package org.example.framework.pages;

import org.example.framework.drivers.Driver;
import org.example.framework.factories.PageObjectFactory;
import org.example.framework.utils.FrameworkLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    private final RegistrationForm registrationForm;

    public RegisterPage() {
        registrationForm = new RegistrationForm();
    }

    /**
     * Performs the registration process with the provided details.
     * After registration, navigates to the specified next page based on the scenario.
     */
     public <T> T performRegistration(String firstName, String middleName, String lastName, String email, String password, String confirmPassword, Class<T> nextPageClass) {
        registrationForm.setFirstName(firstName);
        registrationForm.setMiddleName(middleName);
        registrationForm.setLastName(lastName);
        registrationForm.setEmail(email);
        registrationForm.setPassword(password);
        registrationForm.setConfirmPassword(confirmPassword);
        registrationForm.clickRegisterBtn();
        return PageObjectFactory.createPage(nextPageClass);
    }

    /**
     * Retrieves the expected response message based on the provided operation type.
     */
    public String getOperationResponseMsg(String expectedResult) {
        return switch (expectedResult) {
            case "valid" -> registrationForm.getRegistrationSuccessMsg();
            case "password error" -> registrationForm.getPasswordErrorMsg();
            case "email error" -> registrationForm.getEmailErrorMsg();
            default -> {
                FrameworkLogger.log(getClass(), FrameworkLogger.LogType.ERROR, "Invalid Operation");
                yield "Invalid operation: " + expectedResult;
            }
        };
    }


    private static class RegistrationForm extends BasePage {

        @FindBy(css = "#firstname")
        private WebElement firstName;
        @FindBy(css = "#middlename")
        private WebElement middleName;
        @FindBy(css = "#lastname")
        private WebElement lastName;
        @FindBy(css = "#email_address")
        private WebElement email;
        @FindBy(css = "#password")
        private WebElement password;
        @FindBy(css = "#confirmation")
        private WebElement confirmPassword;
        @FindBy(css = "button[title^='Reg']")
        private WebElement registerBtn;
        @FindBy(css = ".success-msg")
        private WebElement registrationSuccessMsg;
        @FindBy(css = "[id*='advice-validate']")
        private WebElement passwordErrorMsg;
        @FindBy(css = ".error-msg")
        private WebElement emailErrorMsg;

        private void setFirstName(String firstname) {
             sendKeys(getClass(),this.firstName, firstname, "firstName");
        }

        private void setMiddleName(String middleName) {
             sendKeys(getClass(),this.middleName, middleName, "middleName");
        }

        private void setLastName(String lastName) {
            sendKeys(getClass(),this.lastName, lastName, "lastName");
        }

        private void setEmail(String email) {
           sendKeys(getClass(),this.email, email, "email");
        }

        private void setPassword(String password) {
             sendKeys(getClass(),this.password, password, "password");
        }

        private void setConfirmPassword(String confirmPassword) {
             sendKeys(getClass(),this.confirmPassword, confirmPassword, "confirmPassword");
        }

        private void clickRegisterBtn() {
            clickWithJSExecutor(getClass(),this.registerBtn,"Register button");
        }

        private String getRegistrationSuccessMsg() {
            return getTextUsingJavaScript(getClass(), registrationSuccessMsg, "registration success message");
        }

        private String getPasswordErrorMsg() {
            return getText(getClass(),passwordErrorMsg, "password error message");
        }

        private String getEmailErrorMsg() {
            return getText(getClass(),emailErrorMsg, "email error message");
        }

    }

}
