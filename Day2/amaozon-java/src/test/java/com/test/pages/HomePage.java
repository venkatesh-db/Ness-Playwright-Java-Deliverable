package com.test.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page  page;


    public HomePage(Page page) {
        this.page=page;
    }

    public void open(){
        page.navigate("https://www.amazon.in");
    }

    public void search(String item)
    {
        page.locator("#twotabsearchtextbox").fill(item);
        page.locator("#nav-search-submit-button").click();
    }
    public void clickFirstProduct()
    {

    }

}



