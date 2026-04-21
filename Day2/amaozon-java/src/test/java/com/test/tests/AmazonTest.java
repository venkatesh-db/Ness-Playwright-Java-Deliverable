package com.test.tests;

import com.test.base.BaseTest;

import com.test.pages.HomePage;

import com.test.pages.ProductPage;

import  org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AmazonTest  extends BaseTest {

@Test
void searchLaptop(){

    HomePage home = new HomePage(page);

    ProductPage product = new ProductPage(page);

    home.open();

    home.search("laptop");

    home.clickFirstProduct();

    String title =product.getTitle();

    System.out.println("title"+title);

    assertTrue(title.toLowerCase().contains("laptop"));


}

}
