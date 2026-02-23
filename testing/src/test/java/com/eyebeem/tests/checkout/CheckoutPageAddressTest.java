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
 * Tests that checkout page address form displays and accepts input correctly.
 */
@DataDependent
public class CheckoutPageAddressTest extends BaseTest {

    @Test
    public void checkoutPageAddressFormAcceptsValidInput() {
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
        
        // Navigate to cart then checkout via links (preserves SPA state)
        driver.findElement(By.cssSelector("a[href='/cart']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        for (WebElement b : driver.findElements(By.cssSelector("button"))) {
            if ("GO TO CHECKOUT".equals(b.getText())) {
                b.click();
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("checkout-street")));
        
        // Verify all address fields are present
        WebElement streetInput = driver.findElement(By.id("checkout-street"));
        Assert.assertTrue(streetInput.isDisplayed(), "Street input should be visible");
        
        WebElement cityInput = driver.findElement(By.id("checkout-city"));
        Assert.assertTrue(cityInput.isDisplayed(), "City input should be visible");
        
        WebElement zipInput = driver.findElement(By.id("checkout-zip"));
        Assert.assertTrue(zipInput.isDisplayed(), "ZIP input should be visible");
        
        // Fill in address fields
        streetInput.clear();
        streetInput.sendKeys("123 Robot Street");
        
        cityInput.clear();
        cityInput.sendKeys("Tech City");
        
        zipInput.clear();
        zipInput.sendKeys("12345");
        
        // Verify submit button becomes enabled
        WebElement proceedButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(proceedButton.isEnabled(),
                "Proceed button should be enabled when all fields are filled");
    }
}
