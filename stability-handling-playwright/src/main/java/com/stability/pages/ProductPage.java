


package com.stability.pages;

import com.microsoft.playwright.*;

public class ProductPage {

    private Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void addToCart() {

        //  Intentionally wrong locator to trigger failure
        Locator product = page.locator(".inventory_item").first();

        product.waitFor();

        //  wrong text → forces retry
        product.locator("text=Add to cart123").click();
    }
}


/*


package com.stability.pages;

import com.microsoft.playwright.*;

public class ProductPage {

    private Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public void addToCart() {

        Locator product = page.locator(".inventory_item").first();

        // ensures DOM stability
        product.waitFor();

        product.locator("text=Add to cart").click();
    }
}

*/

