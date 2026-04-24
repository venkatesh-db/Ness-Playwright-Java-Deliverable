

package com.stability.tests;

import com.microsoft.playwright.*;
import com.stability.core.DriverFactory;
import com.stability.pages.LoginPage;
import com.stability.pages.ProductPage;
import org.junit.jupiter.api.*;

public class StabilityTest {

    private Page page;

    @BeforeEach
    void setup() {
        page = DriverFactory.createPage();
    }

    @Test
    void testWithStabilityHandling() {

        LoginPage login = new LoginPage(page);
        ProductPage product = new ProductPage(page);

        // Step 1: Login
        login.login();

        // Step 2: Retry logic
        int retries = 0;
        int maxRetries = 3;

        while (retries < maxRetries) {
            try {

                System.out.println("Attempt: " + (retries + 1));

                product.addToCart();

                System.out.println("Step succeeded");
                break;

            } catch (PlaywrightException e) {

                String msg = e.getMessage().toLowerCase();

                // Handle only transient issues
                if (msg.contains("timeout") ||
                        msg.contains("detached") ||
                        msg.contains("waiting for locator")) {

                    retries++;
                    System.out.println("Retry due to transient issue...");

                    page.waitForTimeout(1000);

                } else {
                    throw e; // real failure
                }
            }
        }

        // Step 3: Final validation
        Assertions.assertTrue(
                page.locator(".shopping_cart_badge").isVisible(),
                "Cart badge not visible after retries"
        );

        System.out.println("Test completed successfully");
    }

    @AfterEach
    void tearDown() {
        DriverFactory.close();
    }
}



/*
package com.stability.tests;

import com.microsoft.playwright.*;
import com.stability.core.DriverFactory;
import com.stability.pages.*;
import org.junit.jupiter.api.*;

public class StabilityTest {

    Page page;

    @BeforeEach
    void setup() {
        page = DriverFactory.createPage();
    }

    @Test
    void testWithStabilityHandling() {

        LoginPage login = new LoginPage(page);
        ProductPage product = new ProductPage(page);

        login.login();

        int retries = 0;

        while (retries < 3) {
            try {

                product.addToCart();

                break;

            } catch (PlaywrightException e) {

                if (e.getMessage().contains("timeout") ||
                        e.getMessage().contains("detached")) {

                    System.out.println("Retry due to transient issue...");
                    retries++;
                    page.waitForTimeout(1000);

                } else {
                    throw e; // real failure → don't retry
                }
            }
        }

        Assertions.assertTrue(
                page.locator(".shopping_cart_badge").isVisible()
        );
    }

    @AfterEach
    void tearDown() {
        DriverFactory.close();
    }
}

*/


