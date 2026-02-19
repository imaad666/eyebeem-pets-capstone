package com.eyebeem.tests.orders;

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
 * Tests that orders page displays list of orders correctly.
 */
public class OrdersPageListTest extends BaseTest {

    @Test
    public void ordersPageDisplaysOrderListAfterOrderIsPlaced() {
        // First complete an order
        driver.get(baseUrl + "/products");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".retro-container")));
        
        // Add product
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
        
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio']")));
        
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        if (!radioButtons.isEmpty()) {
            radioButtons.get(0).click();
        }
        
        List<WebElement> payButtons = driver.findElements(By.cssSelector("button[type='submit']"));
        for (WebElement button : payButtons) {
            if (button.getText().contains("PAY")) {
                button.click();
                break;
            }
        }
        
        // Wait for redirect to orders page
        wait.until(ExpectedConditions.urlContains("/orders"));
        
        // Verify orders page displays order
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(heading.getText(), "ORDER HISTORY",
                "Orders page should have ORDER HISTORY heading");
        
        // Verify order cards are displayed
        List<WebElement> orderCards = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertTrue(orderCards.size() > 0,
                "Should display at least one order card");
    }
}
