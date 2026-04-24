
package com.day5.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login() {

        // Navigate to application
        page.navigate("https://www.saucedemo.com/");

        // Enter credentials
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");

        // Click login
        page.click("#login-button");

        // Wait for next page to load
        page.waitForLoadState();
    }
}