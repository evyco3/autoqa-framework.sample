package org.example.framework.pages.account;

import org.example.framework.factories.PageObjectFactory;
import org.example.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBook extends BasePage {
    private final AddressForm addressForm;

    public AddressBook(){
        this.addressForm=new AddressForm();
    }

    public <T>T addNewAddressBook(String firstName,String middleName,String lastName,String company,String telephone,String fax,String address1,String address2,String city,String zip,String country,Class<T>nextPageClass){
        addressForm.clickAddNewAddressBtn();
        waitForPageToLoad(getClass(),"Add New Address");
        addressForm.setFirstName(firstName);
        addressForm.setMiddleName(middleName);
        addressForm.setLastName(lastName);
        addressForm.setCompany(company);
        addressForm.setTelephone(telephone);
        addressForm.setFax(fax);
        addressForm.setAddress1(address1);
        addressForm.setAddress2(address2);
        addressForm.setCity(city);
        addressForm.setZip(zip);
        addressForm.setCountry(country);
        addressForm.clickSaveAddressBtn();
        return PageObjectFactory.createPage(nextPageClass);
    }
    public String getAddNewAddressBookSuccessMsg(){
        return addressForm.getAddressNewBookSuccessMsg();
    }


    private static class AddressForm extends BasePage{
        @FindBy(css = "button[title='Add New Address']")
        private WebElement addNewAddressBtn;
        @FindBy(css = "#firstname")
        private WebElement firstName;
        @FindBy(css = "#middlename")
        private WebElement middleName;
        @FindBy(css = "#lastname")
        private WebElement lastName;
        @FindBy(css = "#company")
        private WebElement company;
        @FindBy(css = "#telephone")
        private WebElement telephone;
        @FindBy(css = "#fax")
        private WebElement fax;
        @FindBy(css = "#street_1")
        private WebElement address1;
        @FindBy(css = "#street_2")
        private WebElement address2;
        @FindBy(css = "#city")
        private WebElement city;
        @FindBy(css = "#zip")
        private WebElement zip;
        @FindBy(css="#country")
        private WebElement country;
        @FindBy(css = "button[title='Save Address']")
        private WebElement saveAddressBtn;
        @FindBy(css = ".success-msg")
        private WebElement addressNewBookSuccessMsg;



        private AddressForm(){
            waitForPageToLoad(getClass(),"Address Book");
        }
        private void clickAddNewAddressBtn(){
            clickWithJSExecutor(getClass(),this.addNewAddressBtn,"add new address button");
        }
        private void setFirstName(String firstName){
            sendKeys(getClass(),this.firstName,firstName,"firstName");
        }
        private void setMiddleName(String middleName){
            sendKeys(getClass(),this.middleName,middleName,"middleName");
        }
        private void setLastName(String lastName){
            sendKeys(getClass(),this.lastName,lastName,"lastName");
        }
        private void setCompany(String company){
            sendKeys(getClass(),this.company,company,"company");
        }
        private void setTelephone(String telephone){
            sendKeys(getClass(),this.telephone,telephone,"telephone");
        }
        private void  setFax(String fax){
            sendKeys(getClass(),this.fax,fax,"fax");
        }
        private void setAddress1(String address1){
            sendKeys(getClass(),this.address1, address1,"street address 1");
        }
        private void setAddress2(String address2){
            sendKeys(getClass(),this.address2, address2,"street address 2");
        }
        private void setCity(String city){
            sendKeys(getClass(),this.city,city,"city");
        }
        private void setZip(String zip){
            sendKeys(getClass(),this.zip,zip,"zip");
        }
        private void setCountry(String country){
            selectByVisibleText(getClass(),this.country,country,"country");
        }
        private void clickSaveAddressBtn(){
            clickWithJSExecutor(getClass(),this.saveAddressBtn,"save address btn");
        }
        private String getAddressNewBookSuccessMsg(){
            return getTextUsingJavaScript(getClass(),this.addressNewBookSuccessMsg,"address new book success message");
        }

    }
}
