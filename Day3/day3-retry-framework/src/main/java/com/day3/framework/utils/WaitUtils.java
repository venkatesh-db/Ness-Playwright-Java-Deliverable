package com.day3.framework.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WaitUtils {

    public static void waitForPageLoad(Page page) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public static void waitForElement(Page page, String selector) {
        page.locator(selector).waitFor();
    }
}