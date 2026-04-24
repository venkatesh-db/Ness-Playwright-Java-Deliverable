

package com.day5.core;

import java.util.*;

public class FlowParser {

    public static List<String> parse(List<String> steps) {

        List<String> actions = new ArrayList<>();

        for (String step : steps) {

            if (step.contains("Login")) actions.add("LOGIN");
            if (step.contains("Open Product")) actions.add("OPEN_PRODUCT");
        }

        return actions;
    }
}