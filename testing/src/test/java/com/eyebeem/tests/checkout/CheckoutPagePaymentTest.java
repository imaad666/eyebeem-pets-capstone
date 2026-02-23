package com.eyebeem.tests.checkout;

import com.eyebeem.tests.BaseTest;
import com.eyebeem.tests.DataDependent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Tests that checkout payment step displays and processes payment correctly.
 */
@DataDependent
public class CheckoutPagePaymentTest extends BaseTest {

    @Test
    public void checkoutPaymentStepDisplaysPaymentOptions() {
        // Add product and fill address
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
        
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        for (WebElement b : driver.findElements(By.cssSelector("button"))) {
            if ("GO TO CHECKOUT".equals(b.getText())) {
                b.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout-street")));
        
        // Fill address and proceed to payment
        driver.findElement(By.id("checkout-street")).sendKeys("123 Robot Street");
        driver.findElement(By.id("checkout-city")).sendKeys("Tech City");
        driver.findElement(By.id("checkout-zip")).sendKeys("12345");
        
        // Submit to proceed to payment
        WebElement proceedButton = driver.findElement(By.cssSelector("button[type='submit']"));
        proceedButton.click();
        
        // Wait for payment step
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio']")));
        
        // Verify payment method options
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        Assert.assertTrue(radioButtons.size() >= 2,
                "Should have at least 2 payment method options");
        
        // Verify payment form heading
        WebElement paymentHeading = driver.findElement(By.cssSelector("h2"));
        Assert.assertTrue(paymentHeading.getText().contains("PAYMENT"),
                "Payment step should have PAYMENT in heading");
    }
}
