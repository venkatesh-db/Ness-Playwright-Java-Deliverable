package com.day4.tests;

import com.day4.base.BaseTest;
import com.day4.core.ThreadLocalManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;

public class EcommerceTest extends BaseTest {

    @Test
    void searchProduct() {

        System.out.println("🔥 Test started | Thread: " + Thread.currentThread().getId());

        Page page = ThreadLocalManager.getPage();

        // Open Google
        page.navigate("https://www.google.com");

        // Wait fully loaded (important for stability)
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        // Handle possible consent popup (safe optional step)
        try {
            if (page.locator("button:has-text('Accept all')").isVisible()) {
                page.click("button:has-text('Accept all')");
            }
        } catch (Exception ignored) {}

        // Wait for search box
        page.waitForSelector("textarea[name='q']");

        // Perform search
        page.fill("textarea[name='q']", "laptop");
        page.press("textarea[name='q']", "Enter");

        // Wait for results
        page.waitForLoadState(LoadState.NETWORKIDLE);

        System.out.println("✅ Search completed | Thread: " + Thread.currentThread().getId());
    }

    @Test
    void openGoogle() {

        System.out.println("🌐 Opening Google | Thread: " + Thread.currentThread().getId());

        Page page = ThreadLocalManager.getPage();

        page.navigate("https://www.google.com");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        System.out.println("✅ Opened Google successfully");
    }
}