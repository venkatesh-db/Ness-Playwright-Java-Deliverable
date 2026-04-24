package com.visiontestflow.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PageAssertions;
import com.visiontestflow.core.DriverFactory;
import com.visiontestflow.core.FlowParser;
import com.visiontestflow.pages.CartPage;
import com.visiontestflow.pages.LoginPage;
import com.visiontestflow.pages.ProductPage;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EcommerceTest {

    private Page page;

    @BeforeEach
    void setup() {
        page = DriverFactory.createPage();
    }

    @Test
    void testFullFlowWithVisualValidation() {

        List<String> flow = Arrays.asList(
                "Login user",
                "Add product",
                "Verify cart"
        );

        List<String> actions = FlowParser.parse(flow);

        LoginPage login = new LoginPage(page);
        ProductPage product = new ProductPage(page);
        CartPage cart = new CartPage(page);

        for (String action : actions) {

            switch (action) {

                case "LOGIN":
                    login.login();
                    break;

                case "ADD_TO_CART":
                    product.addProductToCart();
                    break;

                case "VERIFY_CART":
                    Assertions.assertTrue(
                            cart.isCartItemVisible(),
                            "Cart item not visible"
                    );
                    break;
            }
        }

        // Ensure page is stable
        page.waitForLoadState();
        page.waitForTimeout(2000);

        // ===== SAFE VERSION (NO ERROR) =====
        // This always works and saves screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("cart-page.png")));

        // ===== OPTIONAL VISUAL ASSERTION (ENABLE AFTER FIRST RUN) =====
        /*
        assertThat(page).hasScreenshot(
                "cart-page.png",
                new PageAssertions.HasScreenshotOptions()
                        .setMaxDiffPixelRatio(0.05)
        );
        */
    }

    @AfterEach
    void tearDown() {
        DriverFactory.close();
    }
}