
package com.test;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class PlaywrightDebugTest {


    void bugfreecode(){


        try (Playwright playwright = Playwright.create()) {

            // ✅ Use installed Chrome (no download needed)
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setChannel("chrome")   // uses local Chrome
                            .setHeadless(true));

            BrowserContext context = browser.newContext();

            Page page = context.newPage();


            // Open website
            page.navigate("https://www.amazon.in");

            page.locator("#twotabsearchtextbox").fill("laptop");

            page.locator("#nav-search-submit-button").click();

            page.waitForSelector("div[data-component-type='s-search-result']");


           Locator products=  page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("laptop"));

           Locator firstProduct=   products.first();

            firstProduct.waitFor();

            firstProduct.scrollIntoViewIfNeeded();


            Page productPage ;




            try
            {

                System.out.println("try reached ");

                productPage = context.waitForPage( ()->{

                    System.out.println(" callback  reached ");

                    firstProduct.click(new Locator.ClickOptions().setForce(true) );
                    new BrowserContext.WaitForPageOptions().setTimeout(5000);
                    // click link -> browser open new tab
                });


            } catch (Exception e){

                System.out.println("exception reached ");
                firstProduct.click(new Locator.ClickOptions().setForce(true) );
                productPage=page;
            }

            productPage.waitForLoadState();

            // page full loaded  page reach load state

           Locator title =  productPage.locator("span#productTitle" ).first();

           title.waitFor( new Locator.WaitForOptions().setTimeout(30000));
//
         assertThat(title).isVisible();
//
           System.out.println("product "+title.textContent());

           browser.close();


        }



    }




    void amazonSearchFlow() {


        try (Playwright playwright = Playwright.create()) {

            // ✅ Use installed Chrome (no download needed)
            Browser browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setChannel("chrome")   // uses local Chrome
                            .setHeadless(false));

            BrowserContext context = browser.newContext();

            Page page = context.newPage();


            // Open website
            page.navigate("https://www.amazon.in");

            page.locator("#twotabsearchtextbox").fill("laptop");

            page.locator("#nav-search-submit-button").click();  // element must exist in dom element be visible

            // above are autowait

            page.waitForSelector("div.s-main-slot");
            // manually wait element


            Locator firstproduct = page.locator("div.s-main-slot div[data-component-type='s-search-result']").first();

            firstproduct.scrollIntoViewIfNeeded();

            Page productpage;

            try {
                productpage = context.waitForPage(() -> {
                    firstproduct.click();
                });
            } catch (Exception e) {

                firstproduct.click();
                productpage = page;
            }

            Locator title = productpage.locator("span#productTitle");

            title.waitFor();

           // title.waitFor(new Locator.WaitForOptions().setTimeout(30000));
            // locator object
            // dom dynamic

            assertThat(title).isVisible();
            System.out.println("product title " + title.textContent());
            browser.close();


        }

    }

}
