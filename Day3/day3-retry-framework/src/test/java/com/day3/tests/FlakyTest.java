

package com.day3.tests;

import com.day3.framework.base.BaseTest;
import com.day3.framework.core.PlaywrightManager;
import com.day3.framework.retry.Retry;
import com.day3.framework.retry.RetryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@ExtendWith(RetryExtension.class)
public class FlakyTest extends BaseTest {

    @Test
    @Retry(2)
    void testExample() {

        var page = PlaywrightManager.getPage();

        page.navigate("https://example.com");

        // simulate flaky failure (optional)
        if (Math.random() < 0.3) {
            throw new RuntimeException("Random failure");
        }

        // ✅ FINAL FIX (exact URL match)
        assertThat(page).hasURL("https://example.com/");
    }
}