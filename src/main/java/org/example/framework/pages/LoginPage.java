package org.example.framework.pages;


import org.example.framework.factories.PageObjectFactory;
import org.example.framework.utils.FrameworkLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    private final LoginForm loginForm;
    public LoginPage(){
        this.loginForm=new LoginForm();
    }
    public <T> T performLogin(String email,String password,Class<T>nextPageClass){
        this.loginForm.setEmail(email);
        this.loginForm.sendPassword(password);
        this.loginForm.clickLoginBtn();
        return PageObjectFactory.createPage(nextPageClass);
    }

    public String getOperationResponseMsg(String operation){
        return switch (operation){
            case "valid"->loginForm.getLoginSuccessMsg();
            case "invalid"->loginForm.getLoginFailMsg();
            default -> {
                FrameworkLogger.log(getClass(), FrameworkLogger.LogType.ERROR, "Invalid Operation");
                yield "Invalid operation: " + operation;
            }
        };
    }


    private static class LoginForm extends BasePage{
        @FindBy(css = "#email")
        private WebElement email;
        @FindBy(css = "#pass")
        private WebElement password;
        @FindBy(css = "#send2")
        private WebElement loginBtn;
        @FindBy(css = "p.welcome-msg")
        private WebElement loginSuccessMsg;
        @FindBy(css = ".error-msg")
        private WebElement loginFailMsg;

        private void setEmail(String email){
            sendKeys(getClass(),this.email,email,"email");
        }
        private void sendPassword(String password){
            sendKeys(getClass(),this.password,password,"password");
        }
        private void clickLoginBtn(){
            clickWithJSExecutor(getClass(),this.loginBtn,"loginBtn");
        }
        private String getLoginSuccessMsg(){
            return getText(getClass(),this.loginSuccessMsg,"login success msg");
        }
        private String getLoginFailMsg(){
            return getText(getClass(),this.loginFailMsg,"login fail message");
        }


    }

}
