package com.test.utils;

public class Config {


    public  static final String browser = System.getenv().getOrDefault("Browser","chromium");

    public static final boolean  headless  = Boolean.parseBoolean(System.getenv().getOrDefault("Headless","false")) ;

    public static final boolean  debug = Boolean.parseBoolean(System.getenv().getOrDefault("Debug","false")) ;

    public  static final String channels = System.getenv().getOrDefault("Browser","chromium");


}









