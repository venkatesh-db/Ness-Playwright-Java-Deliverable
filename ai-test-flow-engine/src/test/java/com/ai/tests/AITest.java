

package com.ai.tests;

import com.ai.core.*;
import com.ai.pages.*;
import com.ai.utils.FailureAnalyzer;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import java.util.*;

public class AITest {

    Page page;

    @BeforeEach
    void setup() {
        page = DriverFactory.createPage();
    }

    @Test
    void aiGeneratedFlowTest() {

        try {

            // 🔹 AI Test Creation (input → flow)
            String userInput = "login and open product";

            List<String> flow = AIFlowEngine.generateFlow(userInput);

            LoginPage login = new LoginPage(page);
            ProductPage product = new ProductPage(page);

            // 🔹 AI Flow Execution
            for (String step : flow) {

                System.out.println("Executing: " + step);

                switch (step) {

                    case "LOGIN":
                        login.login();
                        break;

                    case "OPEN_PRODUCT":
                        product.openProduct();
                        break;
                }
            }

            System.out.println("Test Completed Successfully");

        } catch (Exception e) {

            // 🔹 AI Failure Analysis
            FailureAnalyzer.analyze(e);

            throw e;
        }
    }

    @AfterEach
    void tearDown() {
        page.context().browser().close();
    }
}