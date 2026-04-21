
package com.test.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://www.amazon.in");
    }

    public void search(String item) {
        page.locator("#twotabsearchtextbox").fill(item);
        page.locator("#nav-search-submit-button").click();
    }

    public void clickFirstProduct() {

        // Wait for page fully
        page.waitForLoadState();

        // 🔥 Playwright smart locator (BEST PRACTICE)
        Locator product = page.getByRole(
                com.microsoft.playwright.options.AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Laptop")
        ).first();

        // Wait and click
        product.waitFor();

        product.click(new Locator.ClickOptions().setForce(true));

        page.waitForLoadState();
    }
}