

package com.visiontestflow.pages;

import com.microsoft.playwright.Page;

public class CartPage {
    private Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public boolean isCartItemVisible() {
        page.locator(".shopping_cart_link").click();
        page.waitForLoadState();
        return page.locator(".cart_item").first().isVisible();
    }
}