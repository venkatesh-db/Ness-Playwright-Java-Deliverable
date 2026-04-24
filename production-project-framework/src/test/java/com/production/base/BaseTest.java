

package com.production.base;

import com.production.core.ThreadLocalManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setup() {
        ThreadLocalManager.initBrowser();
        ThreadLocalManager.createContextAndPage();
    }

    @AfterEach
    void tearDown() {
        ThreadLocalManager.cleanupContext();
    }

    @AfterAll
    static void cleanUpAll() {
        ThreadLocalManager.cleanupAll();
    }
}