

package com.test.base;

import com.microsoft.playwright.*;
import com.test.utils.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    void setup() {
        playwright = Playwright.create();

        BrowserType type = switch (Config.BROWSER.toLowerCase()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };

        browser = type.launch(new BrowserType.LaunchOptions()
                .setHeadless(Config.HEADLESS)
                .setSlowMo(Config.DEBUG ? 500 : 0));

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/")));

        // tracing for CI debug
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));

        page = context.newPage();
    }

    @AfterEach
    void teardown() {
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

        browser.close();
        playwright.close();
    }
}