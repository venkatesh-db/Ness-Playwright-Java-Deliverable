

package com.day4.utils;

public class PerformanceTracker {

    public static long start() {
        return System.currentTimeMillis();
    }

    public static void end(String testName, long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("⏱ " + testName + " took " + duration + " ms");
    }
}