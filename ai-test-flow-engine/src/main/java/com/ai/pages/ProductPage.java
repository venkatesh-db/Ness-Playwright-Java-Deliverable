

package com.ai.pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    private Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void openProduct() {

        page.locator(".inventory_item").first().click();
    }
}