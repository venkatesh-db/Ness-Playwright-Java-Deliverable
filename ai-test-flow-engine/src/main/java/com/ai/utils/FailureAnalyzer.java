

package com.ai.utils;

public class FailureAnalyzer {

    public static void analyze(Exception e) {

        String msg = e.getMessage();

        if (msg.contains("timeout")) {
            System.out.println("AI Insight: Timeout issue detected");
        } else if (msg.contains("locator")) {
            System.out.println("AI Insight: Locator issue detected");
        } else {
            System.out.println("AI Insight: Unknown failure");
        }
    }
}