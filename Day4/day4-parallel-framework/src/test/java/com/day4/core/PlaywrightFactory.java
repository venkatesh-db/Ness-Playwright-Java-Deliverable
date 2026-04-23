package com.day4.core;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    public static Browser createBrowser(Playwright playwright) {
        return playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }
}