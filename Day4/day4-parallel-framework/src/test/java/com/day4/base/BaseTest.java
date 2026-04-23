

package com.day4.base;

import com.day4.core.ThreadLocalManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    void setup() {
        ThreadLocalManager.init();
    }

    @AfterEach
    void tearDown() {
        ThreadLocalManager.cleanup();
    }
}