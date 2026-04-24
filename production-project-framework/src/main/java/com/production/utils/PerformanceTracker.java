

package com.production.utils;

public class PerformanceTracker {

    public static long start() {
        return System.currentTimeMillis();
    }

    public static void end(String name, long start) {
        long time = System.currentTimeMillis() - start;

        System.out.println(
                "⏱ " + name +
                        " | Thread: " + Thread.currentThread().getId() +
                        " | Time: " + time + " ms"
        );
    }
}