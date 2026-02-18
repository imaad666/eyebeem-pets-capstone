package com.eyebeem.tests.orders;

import com.eyebeem.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Tests that orders page displays empty state correctly when no orders exist.
 */
public class OrdersPageEmptyTest extends BaseTest {

    @Test
    public void ordersPageShowsEmptyMessageWhenNoOrders() {
        driver.get(baseUrl + "/orders");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")));
        
        // Verify page heading
        WebElement heading = driver.findElement(By.cssSelector("h2"));
        Assert.assertEquals(heading.getText(), "ORDER HISTORY",
                "Orders page should have ORDER HISTORY heading");
        
        // Verify empty message
        WebElement emptyMessage = driver.findElement(By.cssSelector(".retro-container"));
        Assert.assertTrue(emptyMessage.getText().contains("No orders") ||
                        emptyMessage.getText().contains("empty"),
                "Empty orders page should display empty message");
        
        // Verify SHOP PRODUCTS link
        WebElement shopLink = driver.findElement(By.linkText("SHOP PRODUCTS"));
        Assert.assertTrue(shopLink.isDisplayed(), "SHOP PRODUCTS link should be visible");
    }
}
