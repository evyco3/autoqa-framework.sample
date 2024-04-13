package org.example.framework.pages;

public class HomePage {

    public static HomePage create() {
        return new HomePage();
    }
    public AccountGate getAccountGate(){
        return new AccountGate();
    }
    public ProductGate getProductGate(){
        return new ProductGate();
    }

    public FooterGate getFooterGate(){
        return new FooterGate();
    }


}
