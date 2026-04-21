

package com.test.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage {

    private final Page page;

    public ProductPage(Page page) {
        this.page = page;
    }


    public String getTitle() {

        Locator title = page.locator("span#productTitle").first();

        title.waitFor();

        return title.textContent();
    }
}