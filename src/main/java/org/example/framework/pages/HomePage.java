package org.example.framework.pages;

import org.example.framework.pages.footer.FooterGate;
import org.example.framework.pages.account.AccountGate;
import org.example.framework.pages.product.ProductGate;

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
