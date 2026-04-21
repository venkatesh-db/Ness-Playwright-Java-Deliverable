
package com.test ;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import org.testng.Assert;

public class TestPlaywright {

    @Test
    void testExample(){

        try( Playwright playwright = Playwright.create()   ){

            //  Playwright playwright   starts driver process open connection to browser engines
            //  Browser browser
            //  Page page

            Browser browser =playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)
            );


            Page page = browser.newPage();

           page.navigate("https://example.com/");

           String actual = page.title();
           String expected = "Example Domain1";

           Assert.assertEquals(actual,expected,"Title msismatch");


           assertThat(page).hasTitle("Example Domain");

           assertThat( page.locator("h1")).hasText("Example Domain");

           assertThat(page.locator("text=Learn more")).isVisible();





//          page.locator("text=Learn more").click();

       //     page.locator("text=Learn more").waitFor(new Locator.WaitForOptions().setTimeout(5000));

            System.out.println("Title:"+ page.title());


            page.getByText("Learn More").click(new Locator.ClickOptions().setTimeout(10));

            Locator learnMore = page.locator("text=Learn more");
// Wait for it to be visible
            learnMore.waitFor(new Locator.WaitForOptions().setTimeout(2000));
// Then click
            learnMore.click();

           // page.waitForTimeout(3000);

            browser.close();

        }

    }

}
