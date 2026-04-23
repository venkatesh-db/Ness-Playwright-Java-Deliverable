package com.day4.base;

import com.day4.core.ThreadLocalManager;
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