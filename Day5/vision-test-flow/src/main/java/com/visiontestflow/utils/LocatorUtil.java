

package com.visiontestflow.utils;

import com.microsoft.playwright.*;

public class LocatorUtil {

    public static Locator find(Page page, String... selectors) {

        for (String selector : selectors) {
            Locator loc = page.locator(selector);
            if (loc.count() > 0) {
                return loc;
            }
        }
        throw new RuntimeException("Element not found: " + String.join(",", selectors));
    }
}