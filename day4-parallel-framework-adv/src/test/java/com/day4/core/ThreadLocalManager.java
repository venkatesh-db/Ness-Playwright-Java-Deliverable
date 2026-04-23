

package com.day4.core;

import com.microsoft.playwright.*;

public class ThreadLocalManager {

    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static void initBrowser() {

        if (playwright.get() == null) {

            playwright.set(Playwright.create());

            browser.set(playwright.get().chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            ));
        }
    }

    public static void createContextAndPage() {

        context.set(browser.get().newContext());
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void cleanupContext() {
        page.get().close();
        context.get().close();
    }

    public static void cleanupAll() {
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
    }
}