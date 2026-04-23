

package com.day4.core;

import com.microsoft.playwright.*;

public class BrowserManager {

    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();

    public static void createContext() {
        Browser browser = ThreadLocalManager.getPage().context().browser();
        context.set(browser.newContext());
    }

    public static Page createPage() {
        return context.get().newPage();
    }
}