

package com.day5.retry;

import com.microsoft.playwright.Page;

import java.util.function.Consumer;

public class RetryUtil {

    public static void runWithRetry(int maxRetries, Consumer<Page> testLogic) {

        int attempt = 0;

        while (attempt < maxRetries) {

            Page page = null;

            try {
                attempt++;
                System.out.println("\n➡️ Attempt: " + attempt);

                // 🔥 fresh browser per attempt
                page = com.day5.core.PlaywrightManager.createFreshPage();

                testLogic.accept(page);

                System.out.println("✅ Test passed on attempt: " + attempt);
                return;

            } catch (Exception e) {

                System.out.println("❌ Failed attempt " + attempt + " reason: " + e.getMessage());

                if (!isRetryable(e)) {
                    System.out.println("🚫 Not retryable");
                    throw e;
                }

                if (attempt >= maxRetries) {
                    System.out.println("💥 Max retries reached");
                    throw e;
                }

                System.out.println("🔁 Retrying...");
            } finally {
                if (page != null) {
                    page.context().browser().close();
                }
            }
        }
    }

    private static boolean isRetryable(Exception e) {
        String msg = e.getMessage();

        return msg != null && (
                msg.contains("timeout") ||
                        msg.contains("network") ||
                        msg.contains("Random failure")
        );
    }
}