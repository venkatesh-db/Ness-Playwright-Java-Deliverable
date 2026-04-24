

package com.production.tests;

import com.production.base.BaseTest;
import com.production.core.ThreadLocalManager;
import com.production.utils.PerformanceTracker;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

public class EcommerceTest extends BaseTest {

    @Test
    void test1_searchLaptop() {
        runTest("Laptop");   // ✅ FIXED
    }

    @Test
    void test2_searchMobile() {
        runTest("Mobile");   // ✅ FIXED
    }

    @Test
    void test3_searchShoes() {
        runTest("Shoes");
    }

    @Test
    void test4_searchWatch() {
        runTest("Watch");
    }

    @Test
    void test5_searchBooks() {
        runTest("Books");
    }

    private void runTest(String testName) {

        long start = PerformanceTracker.start();

        Page page = ThreadLocalManager.getPage();

        page.navigate("https://www.bing.com");

        page.waitForSelector("#sb_form_q");

        page.fill("#sb_form_q", testName);
        page.press("#sb_form_q", "Enter");

        System.out.println("✅ " + testName +
                " | Thread: " + Thread.currentThread().getId());

        PerformanceTracker.end(testName, start);
    }
}