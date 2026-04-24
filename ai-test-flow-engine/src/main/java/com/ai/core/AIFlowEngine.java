
package com.ai.core;

import java.util.*;

public class AIFlowEngine {

    // Simulates AI converting text → test steps
    public static List<String> generateFlow(String input) {

        List<String> steps = new ArrayList<>();

        if (input.contains("login")) steps.add("LOGIN");
        if (input.contains("product")) steps.add("OPEN_PRODUCT");

        return steps;
    }
}