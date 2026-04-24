

package com.visiontestflow.pages;

import com.microsoft.playwright.*;
import com.visiontestflow.utils.LocatorUtil;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login() {

        page.navigate("https://www.saucedemo.com/");

        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");

        Locator loginBtn = LocatorUtil.find(
                page,
                "#login-button",
                "text=Login"
        );

        loginBtn.click();
    }
}