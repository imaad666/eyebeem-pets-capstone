package com.eyebeem.tests.cart;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Tests that cart page checkout button navigates to checkout page.
 */
public class CartPageCheckoutTest extends BaseTest {

    @Test
    public void checkoutButtonNavigatesToCheckoutPage() {
        // Add product to cart first
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
        for (WebElement button : buttons) {
            if (button.getText().contains("ADD TO CART")) {
                button.click();
                break;
            }
        }
        
        // Navigate to cart
        driver.get(baseUrl + "/cart");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Find and click GO TO CHECKOUT button
        List<WebElement> checkoutButtons = driver.findElements(By.cssSelector("button"));
        WebElement checkoutButton = null;
        for (WebElement button : checkoutButtons) {
            if (button.getText().contains("CHECKOUT")) {
                checkoutButton = button;
                break;
            }
        }
        
        Assert.assertNotNull(checkoutButton, "Should find GO TO CHECKOUT button");
        checkoutButton.click();
        
        // Wait for checkout page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout-street")));
        
        // Verify we're on checkout page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/checkout"),
                "Should navigate to checkout page");
        
        WebElement checkoutHeading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(checkoutHeading.getText().contains("CHECKOUT"),
                "Checkout page should have CHECKOUT heading");
    }
}
