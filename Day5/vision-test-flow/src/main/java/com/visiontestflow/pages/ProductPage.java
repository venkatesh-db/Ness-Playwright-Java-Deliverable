package com.visiontestflow.pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    private Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void addProductToCart() {

        // Click first product's add to cart button
        page.locator(".inventory_item").first()
                .locator("text=Add to cart")
                .click();
    }
}