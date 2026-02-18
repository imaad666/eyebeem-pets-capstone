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
 * Tests that orders page displays order details correctly.
 */
public class OrdersPageDetailsTest extends BaseTest {

    @Test
    public void ordersPageDisplaysOrderDetailsCorrectly() {
        // Place an order first
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
        
        driver.get(baseUrl + "/checkout");
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
        
        wait.until(ExpectedConditions.urlContains("/orders"));
        
        // Verify order details are displayed
        List<WebElement> orderCards = driver.findElements(By.cssSelector(".retro-container"));
        Assert.assertTrue(orderCards.size() > 0, "Should have at least one order");
        
        WebElement firstOrder = orderCards.get(0);
        String orderText = firstOrder.getText();
        
        // Verify order contains key information
        Assert.assertTrue(orderText.contains("Order #") || orderText.contains("Order:"),
                "Order should display order ID");
        Assert.assertTrue(orderText.contains("TOTAL") || orderText.contains("$"),
                "Order should display total amount");
    }
}
