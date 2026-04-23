

package com.day5.core;

import com.microsoft.playwright.*;

public class PlaywrightManager {

    public static Page createFreshPage() {

        Playwright pw = Playwright.create();

        Browser browser = pw.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        BrowserContext context = browser.newContext();

        return context.newPage();
    }
}