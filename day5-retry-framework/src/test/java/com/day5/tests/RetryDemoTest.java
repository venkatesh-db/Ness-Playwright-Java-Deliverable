
package com.day5.tests;

import com.day5.retry.RetryUtil;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

public class RetryDemoTest {

    @Test
    void flakyTest() {

        RetryUtil.runWithRetry(3, (Page page) -> {

            System.out.println("🔥 Running flaky test");

            page.navigate("https://example.com");

            // simulate failure
            if (Math.random() < 0.9) {
                throw new RuntimeException("Random failure");
            }

            System.out.println("🎯 Test passed");
        });
    }
}