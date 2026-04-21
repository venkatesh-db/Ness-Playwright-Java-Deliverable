


package com.test;


import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightDebugTest {

    @Test
    void amazonSearchFlow() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // Open site
            page.navigate("https://www.amazon.in");

            // Search
            page.locator("#twotabsearchtextbox").fill("laptop");
            page.locator("#nav-search-submit-button").click();

            // Wait for results
            page.waitForSelector("div.s-main-slot");

            // First product
            Locator firstProduct = page.locator("div.s-main-slot div[data-component-type='s-search-result']")
                    .first();

            firstProduct.scrollIntoViewIfNeeded();

            // 🔥 HANDLE BOTH CASES (new tab + same tab)
            Page productPage;

            try {
                productPage = context.waitForPage(() -> {
                    firstProduct.click();
                });
            } catch (Exception e) {
                // fallback: same page
                firstProduct.click();
                productPage = page;
            }

            // 🔥 WAIT FOR PRODUCT TITLE (robust)
            Locator title = productPage.locator("span#productTitle");

            title.waitFor(new Locator.WaitForOptions().setTimeout(30000));

            assertThat(title).isVisible();

            System.out.println("Product Title: " + title.textContent());

            browser.close();
        }
    }
}




