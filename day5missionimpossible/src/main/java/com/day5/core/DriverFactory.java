

package com.day5.core;

import com.microsoft.playwright.*;

public class DriverFactory {

    public static Page createPage() {
        Playwright pw = Playwright.create();

        Browser browser = pw.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        return browser.newPage();
    }
}