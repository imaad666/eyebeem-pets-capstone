package com.eyebeem.tests.cart;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/** Tests that empty cart SHOP PRODUCTS link navigates to products. */
public class CartPageShopProductsLinkTest extends BaseTest {

    @Test
    public void shopProductsLinkNavigatesToProducts() {
        driver.get(baseUrl + "/cart");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("SHOP PRODUCTS")));
        Assert.assertTrue(link.getAttribute("href").contains("/products"), "Link should point to products");
        link.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("product-search")));
        Assert.assertTrue(driver.getCurrentUrl().contains("/products"), "Should navigate to products page");
    }
}
