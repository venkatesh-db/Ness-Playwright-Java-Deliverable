
package com.stability.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login() {

        page.navigate("https://www.saucedemo.com/");

        // Better than waitForLoadState
        page.waitForSelector("#user-name");

        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");

        page.click("#login-button");

        // Wait for next page
        page.waitForSelector(".inventory_list");
    }
}