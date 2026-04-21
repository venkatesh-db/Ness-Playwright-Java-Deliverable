package com.test.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

import com.test.utils.Config;

public class BaseTest {

  protected  Browser browser;
   protected  Playwright playwright;
   protected  BrowserContext context;
   protected  Page page;


   @BeforeEach
   void setup()
   {

       playwright = Playwright.create();

       BrowserType type = switch ( Config.browser.toLowerCase()){

           case "firefox"->
               playwright.firefox();

           case "webkit"->

               playwright.webkit();

           default ->

               playwright.chromium();
       };


       browser=type.launch(new BrowserType.LaunchOptions().
               setChannel(Config.channels).setHeadless(Config.headless).setSlowMo(Config.debug? 500:0));

      context=   browser.newContext(new Browser.NewContextOptions().setRecordVideoDir( Paths.get("videos/")));

     context.tracing().start( new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));

     //   every action + dom state + screenshoots
       // trace.zip


     page = context.newPage();

     // new tab inside browser


   }

   @AfterEach
   void teardown()
   {
       context.tracing().stop( new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
       context.close();
       browser.close();
       playwright.close();


   }

}
