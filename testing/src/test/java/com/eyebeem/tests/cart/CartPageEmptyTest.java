package com.eyebeem.tests.cart;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests that the cart page displays correctly when cart is empty.
 */
public class CartPageEmptyTest extends BaseTest {

    @Test
    public void cartPageShowsEmptyMessageWhenCartIsEmpty() {
        driver.get(baseUrl + "/cart");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify page heading
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(heading.getText(), "CART",
                "Cart page should have CART heading");
        
        // Verify empty cart message
        WebElement emptyMessage = driver.findElement(By.cssSelector(".retro-container"));
        Assert.assertTrue(emptyMessage.getText().contains("empty"),
                "Empty cart should display empty message");
        
        // Verify "SHOP PRODUCTS" link is present
        WebElement shopLink = driver.findElement(By.linkText("SHOP PRODUCTS"));
        Assert.assertTrue(shopLink.isDisplayed(), "SHOP PRODUCTS link should be visible");
        Assert.assertTrue(shopLink.getAttribute("href").contains("/products"),
                "SHOP PRODUCTS link should point to /products");
    }
}
