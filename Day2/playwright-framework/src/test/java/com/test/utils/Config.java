package com.test.utils;

public class Config {
    public static final String BROWSER =
            System.getenv().getOrDefault("BROWSER", "chromium");

    public static final boolean HEADLESS =
            Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "false"));

    public static final boolean DEBUG =
            Boolean.parseBoolean(System.getenv().getOrDefault("DEBUG", "false"));
}