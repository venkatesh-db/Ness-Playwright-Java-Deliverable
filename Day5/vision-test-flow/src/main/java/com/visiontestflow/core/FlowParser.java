

package com.visiontestflow.core;

import java.util.*;

public class FlowParser {

    public static List<String> parse(List<String> steps) {

        List<String> actions = new ArrayList<>();

        for (String step : steps) {

            if (step.toLowerCase().contains("login")) {
                actions.add("LOGIN");
            } else if (step.toLowerCase().contains("search")) {
                actions.add("SEARCH");
            } else if (step.toLowerCase().contains("add")) {
                actions.add("ADD_TO_CART");
            } else if (step.toLowerCase().contains("verify")) {
                actions.add("VERIFY_CART");
            }
        }
        return actions;
    }
}