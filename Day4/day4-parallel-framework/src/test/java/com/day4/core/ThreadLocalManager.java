

package com.day4.core;

import com.microsoft.playwright.*;

public class ThreadLocalManager {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void init() {
        playwright.set(Playwright.create());

        browser.set(playwright.get().chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        ));

        page.set(browser.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void cleanup() {
        page.get().close();
        browser.get().close();
        playwright.get().close();
    }
}