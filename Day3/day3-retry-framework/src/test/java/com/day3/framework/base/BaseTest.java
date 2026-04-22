
package com.day3.framework.base;

import com.day3.framework.core.PlaywrightManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setup() {
        PlaywrightManager.init();
    }

    @AfterEach
    void teardown() {
        PlaywrightManager.close();
    }
}