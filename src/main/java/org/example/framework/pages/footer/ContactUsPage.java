package org.example.framework.pages.footer;

import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {
    private final ContactUsForm contactUsForm;

    public ContactUsPage(){
        this.contactUsForm= new ContactUsForm();
    }

    public <T> T performContactUs(String name, String email, String telephone, String comment, Class<T> nextPageClass){
        contactUsForm.setName(name);
        contactUsForm.setEmail(email);
        contactUsForm.setTelephone(telephone);
        contactUsForm.setComment(comment);
        contactUsForm.clickSubmitBtn();
        return PageObjectFactory.createPage(nextPageClass);
    }
    public String getErrorMsg()  {
       return contactUsForm.getErrorMsg();
    }


    private static class ContactUsForm extends BasePage{
        @FindBy(css = "#name")
        private WebElement name;
        @FindBy(css = "#email")
        private WebElement email;
        @FindBy(css = "#telephone")
        private WebElement telephone;
        @FindBy(css = "#comment")
        private WebElement comment;
        @FindBy(css = ".buttons-set>button")
        private WebElement submitBtn;
        @FindBy(css = ".error-msg")
        private WebElement errorMsg;

        public ContactUsForm(){
            waitForPageToLoad(getClass(),"Contact Us");
        }

        private void setName(String name) {
            sendKeys(getClass(),this.name,name,"name");
        }

        private void setEmail(String email) {
            sendKeys(getClass(),this.email,email,"email");
        }

        private void setTelephone(String telephone) {
           sendKeys(getClass(),this.telephone,telephone,"telephone");
        }

        private void setComment(String comment) {
            sendKeys(getClass(),this.comment, comment,"comments");
        }

        private void clickSubmitBtn() {
           clickWithJSExecutor(getClass(),this.submitBtn,"submit button");
        }
        private String getErrorMsg(){
            return getTextUsingJavaScript(getClass(),this.errorMsg,"error message");
        }
    }
}
