package org.example.framework.factories;

import lombok.Getter;
import lombok.SneakyThrows;
@Getter
public class PageObjectFactory<T> {

    private final T pageObject;

    private PageObjectFactory(T pageObject) {
        this.pageObject = pageObject;
    }

    @SneakyThrows
    public static <T> T createPage(Class<T> pageClass) {
        T pageObject = pageClass.getDeclaredConstructor().newInstance();
        return  new PageObjectFactory<>(pageObject).getPageObject();
    }


}