package com.eyebeem.tests.products;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that products page has visible search input. */
public class ProductsPageSearchInputTest extends BaseTest {

    @Test
    public void productsPageHasSearchInput() {
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-search")));
        Assert.assertTrue(search.isDisplayed(), "Search input should be visible");
        Assert.assertEquals(search.getAttribute("type"), "text", "Search should be text input");
    }
}
