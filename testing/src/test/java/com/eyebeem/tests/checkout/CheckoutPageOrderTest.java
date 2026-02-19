package com.eyebeem.tests.checkout;

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
 * Tests that completing checkout creates an order successfully.
 */
public class CheckoutPageOrderTest extends BaseTest {

    @Test
    public void checkoutCompletesOrderSuccessfully() {
        // Add product
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
        
        // Go to cart then checkout (preserves SPA state)
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        for (WebElement b : driver.findElements(By.cssSelector("button"))) {
            if ("GO TO CHECKOUT".equals(b.getText())) {
                b.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout-street")));
        
        driver.findElement(By.id("checkout-street")).sendKeys("123 Robot Street");
        driver.findElement(By.id("checkout-city")).sendKeys("Tech City");
        driver.findElement(By.id("checkout-zip")).sendKeys("12345");
        
        WebElement proceedButton = driver.findElement(By.cssSelector("button[type='submit']"));
        proceedButton.click();
        
        // Wait for payment step
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio']")));
        
        // Select payment method and pay
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        if (!radioButtons.isEmpty()) {
            radioButtons.get(0).click();
        }
        
        // Find and click PAY button
        List<WebElement> payButtons = driver.findElements(By.cssSelector("button[type='submit']"));
        WebElement payButton = null;
        for (WebElement button : payButtons) {
            if (button.getText().contains("PAY")) {
                payButton = button;
                break;
            }
        }
        
        Assert.assertNotNull(payButton, "Should find PAY button");
        payButton.click();
        
        // Wait for order confirmation
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify order confirmation message
        WebElement confirmationHeading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(confirmationHeading.getText().contains("CONFIRMED") ||
                        confirmationHeading.getText().contains("SUCCESSFUL"),
                "Should show order confirmation message");
    }
}
