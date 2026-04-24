

package com.day5.pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void openProduct() {

        page.locator(".inventory_item").first().click();
    }
}