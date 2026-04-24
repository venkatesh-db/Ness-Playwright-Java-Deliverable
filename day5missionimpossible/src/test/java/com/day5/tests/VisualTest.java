
package com.day5.tests;

import com.day5.core.DriverFactory;
import com.day5.core.FlowParser;
import com.day5.pages.LoginPage;
import com.day5.pages.ProductPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import java.nio.file.*;
import java.util.*;

public class VisualTest {

    private Page page;

    @BeforeEach
    void setup() {
        page = DriverFactory.createPage();
    }

    @Test
    void visualTestingFlow() throws Exception {

        // 🔹 AI Generated Test Flow
        List<String> flow = Arrays.asList(
                "Login user",
                "Open Product"
        );

        List<String> actions = FlowParser.parse(flow);

        LoginPage login = new LoginPage(page);
        ProductPage product = new ProductPage(page);

        for (String action : actions) {

            switch (action) {

                case "LOGIN":
                    login.login();
                    break;

                case "OPEN_PRODUCT":
                    product.openProduct();
                    break;
            }
        }

        // 🔹 Visual Testing Fundamentals (stability)
        page.waitForLoadState();
        page.waitForTimeout(2000);

        // 🔹 Handling Dynamic Content
        if (page.locator(".inventory_details_price").count() > 0) {
            page.locator(".inventory_details_price")
                    .evaluate("el => el.style.visibility='hidden'");
        }

        // 🔹 Paths
        Path actualPath = Paths.get("actual.png");
        Path baselinePath = Paths.get("src/test/resources/baseline.png");

        // 🔹 Take current screenshot
        page.screenshot(new Page.ScreenshotOptions().setPath(actualPath));

        // 🔹 Snapshot Testing (Create baseline if not exists)
        if (!Files.exists(baselinePath)) {

            Files.createDirectories(baselinePath.getParent());
            Files.copy(actualPath, baselinePath);

            System.out.println("Baseline created. Re-run test.");
            return;
        }

        // 🔹 Screenshot Comparison (Simple)
        byte[] actualBytes = Files.readAllBytes(actualPath);
        byte[] baselineBytes = Files.readAllBytes(baselinePath);

        Assertions.assertTrue(
                Arrays.equals(actualBytes, baselineBytes),
                "Visual mismatch detected!"
        );

        System.out.println("Visual comparison passed");
    }

    @AfterEach
    void tearDown() {
        page.context().browser().close();
    }
}