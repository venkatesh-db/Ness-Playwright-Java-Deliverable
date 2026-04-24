

package com.stability.core;

import com.microsoft.playwright.*;

public class DriverFactory {

    private static Playwright playwright;
    private static Browser browser;

    public static Page createPage() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        return browser.newContext().newPage();
    }

    public static void close() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}